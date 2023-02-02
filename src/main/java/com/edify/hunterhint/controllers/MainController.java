package com.edify.hunterhint.controllers;

import com.edify.hunterhint.models.*;
import com.edify.hunterhint.repositories.*;
import com.edify.hunterhint.services.HuntingFarmService;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final HuntingFarmService huntingFarmService;
    private final HuntingFarmRep huntingFarmRep;
    private final HuntingOfferRep offerRep;
    private final MunicipalDistrictRep districtRep;
    private final RegionRep regionRep;
    private final HuntingResourcesRep resourcesRep;
    private final BookingRep bookingRep;
    private final ResourcesCostRep resourcesCostRep;
    private final ImageRepository imageRepository;
    private final ImageLinkRep imageLinkRep;
    private final UserRep userRep;
    private final UserNameRep userNameRep;
    private final CompanyNameRep companyNameRep;


    private static final String cryptographySalt = "He that tilleth his land shall have plenty of bread: but he that followeth after vain persons shall have poverty enough.";

    private User session = null;
    private final String[] methodArr = new String[]{"Все способы", "Вольерная охота", "Натаска и нагонка собак",
            "Организация загонной охоты", "Организация охоты на реву", "Организация охоты с гончими собаками",
            "Организация охоты с засидки", "Организация охоты с лайками", "Организация охоты с легавыми собаками",
            "Организация охоты с луком/арболетом", "Организация охоты с подхода", "Самостоятельная охота"};

    private final String[] guidingArr = new String[]{"Не выбрано", "Охота с егерем", "Охота без сопровождения", "Частичное сопровождение"};

    @GetMapping("/")
    public String mainPage(@RequestParam(name = "region", required = false) String region,
                           @RequestParam(name = "district", required = false) String district,
                           @RequestParam(name = "huntingResource", required = false) String huntingResource,
                           @RequestParam(name = "hotel", required = false) boolean hotel,
                           @RequestParam(name = "bath", required = false) boolean bath,
                           @RequestParam(name = "noDate", required = false) boolean noDate,
                           @RequestParam(name = "checkInDate", required = false) String checkInDate,
                           @RequestParam(name = "leaveDate", required = false) String leaveDate,
                           @RequestParam(name = "guests", required = false) Long guests,
                           @RequestParam(name = "hunters", required = false) Long hunters,
                           @RequestParam(name = "price", required = false) Long price,
                           @RequestParam(name = "guiding", required = false) String guiding,
                           @RequestParam(name = "method", required = false) String method, Model model) {

        if (guests == null || guests < 0L) {
            guests = 0L;
        }
        if (hunters == null || hunters < 0L) {
            hunters = 1L;
        }
        short quantity = (short) (guests + hunters);
        LocalDate checkIn;
        if (checkInDate == null || checkInDate.isEmpty()) {
            checkIn = LocalDate.MIN;
        } else {
            checkIn = LocalDate.parse(checkInDate);
        }
        LocalDate leave;
        if (leaveDate == null || leaveDate.isEmpty()) {
            leave = LocalDate.MIN;
        } else {
            leave = LocalDate.parse(leaveDate);
        }
        if (noDate) {
            checkIn = LocalDate.MIN;
            leave = LocalDate.MIN;
        }
        if (price == null || price < 0) {
            price = (long) Integer.MAX_VALUE;
        }
        if (method == null || method.isEmpty()) {
            method = "0";
        }
        if (guiding == null || guiding.isEmpty()) {
            guiding = "0";
        }

        List<HuntingFarm> huntingFarms = huntingFarmService.findByRegionAndMd(region, district);
        huntingFarms = huntingFarmService.bathAndHotelSearch(hotel, quantity, bath, huntingFarms);
        huntingFarms = huntingFarmService.findByDateAndCostAndOther(huntingFarms, checkIn, leave, quantity, hotel,
                huntingResource, price.intValue(), Integer.parseInt(guiding), Integer.parseInt(method));
        for (HuntingFarm farm : huntingFarms) {
            farm.setRegionName(regionRep.findById((long) farm.getRegionCode()).get().getName());
            farm.setMunicipalDistrictName(districtRep.findById((long) farm.getMunicipalDistrictId()).get().getName());
        }
        model.addAttribute("farms", huntingFarms);
        boolean isOwnerOrAdmin;
        boolean sessionNotNull;
        if (session != null) {
            sessionNotNull = true;
            isOwnerOrAdmin = session.isOwnerOrAdmin();
        } else {
            sessionNotNull = false;
            isOwnerOrAdmin = false;
        }
        model.addAttribute("isOwnerOrAdmin", isOwnerOrAdmin);
        model.addAttribute("sessionNotNull", sessionNotNull);

        return "mainPage";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id,
                              @RequestParam(name = "checkbox", required = false) boolean checkbox,
                              Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        User user = userRep.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/";
        }
        if (!session.isAdmin() && session.getId() != user.getId()) {
            return "redirect:/";
        }

        if (checkbox) {
            userRep.deleteById((long) user.getId());
            if (session.getId() == user.getId()) {
                return "redirect:/logout";
            }
            return "redirect:/";
        }
        model.addAttribute("id", ""+id);
        return "deleteUser";
    }

    @GetMapping("/users")
    public String users(Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        if (!session.isAdmin()) {
            return "redirect:/";
        }
        List<User> users = userRep.findAll();
        for (User user:
             users) {
            user.setNameSrt(userNameRep.findById((long) user.getNameId()).get().getName());
            if (user.isAdmin()) {
                user.setAccessLevelStr("администратор");
            }
            if (user.isOwner()) {
                user.setAccessLevelStr("директор");
            }
            if (user.isHunter()) {
                user.setAccessLevelStr("пользователь");
            }
        }

        model.addAttribute("users", users);
        return "userList";
    }


    @GetMapping("/personal")
    public String personal(
            Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        List<ImageLink> imageLinks = imageLinkRep.findAllByOwnerId(session.getId());
        ImageLink imageLink;
        if (imageLinks.isEmpty()) {
            imageLink = imageLinkRep.findAllByOwnerId(500000000).get(0);
        } else {
            imageLink = imageLinks.get(0);
        }
        String userName = userNameRep.findById((long) session.getNameId()).get().getName();
        session.setNameSrt(userName);

        model.addAttribute("thisOwnerOrAdmin", session.isOwnerOrAdmin());
        model.addAttribute("isAdmin", session.isAdmin());
        model.addAttribute("img", imageLink);
        model.addAttribute("user", session);
        return "/personal";
    }

    @GetMapping("/personality/{id}")
    public String personality(@PathVariable Long id, Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        if (!session.isAdmin()) {
            return "redirect:/";
        }
        User user = userRep.findById(id).get();
        List<ImageLink> imageLinks = imageLinkRep.findAllByOwnerId(Math.toIntExact(id));
        ImageLink imageLink;
        if (imageLinks.isEmpty()) {
            imageLink = imageLinkRep.findAllByOwnerId(500000000).get(0);
        } else {
            imageLink = imageLinks.get(0);
        }
        String userName = userNameRep.findById((long) user.getNameId()).get().getName();
        user.setNameSrt(userName);

        model.addAttribute("thisOwnerOrAdmin", user.isOwnerOrAdmin());
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("img", imageLink);
        model.addAttribute("user", user);
        return "personal";
    }

    @GetMapping("/bookings")
    public String bookingList(Model model){
        if (session == null) {
            return "redirect:/login";
        }
        List<Booking> bookings = new ArrayList<>();
        if (session.isHunter()) {
            bookings = bookingRep.findByUserId(session.getId());
        } else {
            if (session.isOwner()) {
                List<HuntingFarm> huntingFarms = huntingFarmRep.findByUserId(session.getId());
                for (HuntingFarm farm:
                     huntingFarms) {
                    bookings.addAll(bookingRep.findByFarmId(farm.getId()));
                }
            } else {
                bookings = bookingRep.findAll();
            }
        }

        for (Booking booking:
                bookings) {
            User user = userRep.findById((long) booking.getUserId()).orElse(null);
            String name;
            if (user != null) {
                name = userNameRep.findById((long) user.getNameId()).get().getName();
                booking.setUserName(user.getLastLame() + " " + name + " " + user.getPatronymic());
            } else {
                booking.setUserName("Информацияя о пользователе удалена");
            }
            String farmName;
            if (booking.getFarmId() == null) {
                farmName = "Информацияя о гостинице удалена";
            } else {
                farmName = huntingFarmRep.findById(booking.getFarmId()).get().getName();
            }
            booking.setFarmName(farmName);
        }
        model.addAttribute("bookings", bookings);
        return "bookingList";
    }

    @GetMapping("/areas")
    public String farmList(Model model){
        if (session == null) {
            return "redirect:/login";
        }
        if (session.isHunter()) {
            return "redirect:/";
        }
        List<HuntingFarm> farms = new ArrayList<>();
        if (session.isOwner()) {
            farms = huntingFarmRep.findByUserId(session.getId());
        }
        if (session.isAdmin()) {
            farms = huntingFarmRep.findAll();
        }

        for (HuntingFarm farm:
             farms) {
            farm.setCompanyStr(companyNameRep.findById((long) farm.getCompanyId()).get().getName());
            farm.setRegionName(regionRep.findById((long) farm.getRegionCode()).get().getName());
            farm.setMunicipalDistrictName(districtRep.findById((long) farm.getMunicipalDistrictId()).get().getName());
        }
        model.addAttribute("farms", farms);
        return "farmsList";
    }

    @GetMapping("/logout")
    public String logout() {
        session = null;
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(@RequestParam(name = "email", required = false) String email,
                        @RequestParam(name = "password", required = false) String password, Model model) {
        String message;
        User user = userRep.findByEmail(email).orElse(null);
        if (user != null) {
            String originalString = password + cryptographySalt + user.getEmail();
            String sha256hex = Hashing.sha256()
                    .hashString(originalString, StandardCharsets.UTF_8)
                    .toString();
            if (sha256hex.equals(user.getHashValue())) {
                System.out.println(sha256hex.length());
            }
            session = user;
            return "redirect:/";
        }
        message = "Пользователь с email " + email + " ещё не зарегестрирован";
        model.addAttribute("message", message);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(@RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "lastName", required = false) String lastName,
                               @RequestParam(name = "patronymic", required = false) String patronymic,
                               @RequestParam(name = "email", required = false) String email,
                               @RequestParam(name = "phone", required = false) String phone,
                               @RequestParam(name = "password", required = false) String password,
                               Model model) {
        String message = "";
        if (name != null && !name.isEmpty() && lastName != null && !lastName.isEmpty() && patronymic != null && !patronymic.isEmpty() &&
                email != null && !email.isEmpty() && phone != null && !phone.isEmpty() && password != null && !password.isEmpty()) {
            List<UserName> userNames = userNameRep.findAllByName(name);
            UserName userName;
            if (userRep.findByEmail(email).orElse(null) != null) {
                message = "Пользователь с email " + email + " уже зарегестрирован";
                model.addAttribute("message", message);
                return "reg";
            }
            if (userNames.isEmpty()) {
                userName = userNameRep.save(new UserName(-1, name));
            } else {
                userName = userNames.get(0);
            }

            String originalString = password + cryptographySalt + email;
            String sha256hex = Hashing.sha256()
                    .hashString(originalString, StandardCharsets.UTF_8)
                    .toString();

            User user = new User(-1, userName.getId(), lastName, patronymic, email, phone, sha256hex, -1, null,null);
            user = userRep.save(user);
            session = user;
            return "redirect:/";
        }
        model.addAttribute("message", message);
        return "reg";
    }

    @GetMapping("/area/add")
    public String addFarm(@RequestParam(name = "region", required = false) String region,
                          @RequestParam(name = "district", required = false) String district,
                          @RequestParam(name = "name", required = false) String name,
                          @RequestParam(name = "com", required = false) String company,
                          @RequestParam(name = "hotel", required = false) boolean hotel,
                          @RequestParam(name = "bath", required = false) boolean bath,
                          @RequestParam(name = "hotelPrice", required = false) Long hotelPrice,
                          @RequestParam(name = "hotelCapacity", required = false) Long hotelCapacity,
                          @RequestParam(name = "coordinatesOfCampLatitude", required = false) String coordinatesOfCampLatitude,
                          @RequestParam(name = "coordinatesOfCampLongitude", required = false) String coordinatesOfCampLongitude,
                          @RequestParam(name = "area", required = false) String area,
                          @RequestParam(name = "maxNumberHunters", required = false) Long maxNumberHunters,
                          @RequestParam(name = "description", required = false) String description) {
        if (session != null && (session.isAdmin() || session.isOwner())) {
            if (region != null && !region.isEmpty() && district != null && !district.isEmpty() && name != null && !name.isEmpty() &&
                    hotelPrice != null && hotelCapacity != null && coordinatesOfCampLatitude != null &&
                    coordinatesOfCampLongitude != null && !coordinatesOfCampLatitude.isEmpty() && !coordinatesOfCampLongitude.isEmpty() && area != null &&
                    !area.isEmpty() && maxNumberHunters != null && maxNumberHunters > 0L && company != null && !company.isEmpty()) {
                if (!hotel || hotelPrice >= 0L && hotelCapacity > 0L) {
                    float areaFloat = Float.parseFloat(area);
                    double coordinatesOfCampLatitudeDouble = Double.parseDouble(coordinatesOfCampLatitude);
                    double coordinatesOfCampLongitudeDouble = Double.parseDouble(coordinatesOfCampLongitude);
                    if (description != null && description.isEmpty()) {
                        description = "Описание не указано";
                    }
                    double[] coordinates = {coordinatesOfCampLatitudeDouble, coordinatesOfCampLongitudeDouble};

                    List<CompanyName> companyNames = companyNameRep.findByName(company);
                    CompanyName companyName;
                    if (companyNames.isEmpty()) {
                        companyName = companyNameRep.save(new CompanyName(-1, company));
                    } else {
                        companyName = companyNames.get(0);
                    }

                    HuntingFarm farm = new HuntingFarm(-1, session.getId(), companyName.getId(), (short) -1, -1, name, coordinates, areaFloat, hotel, bath,
                            maxNumberHunters.shortValue(), hotelCapacity.shortValue(), hotelPrice.intValue(), description, region, district, -1, null);
                    farm = huntingFarmService.upload(farm);
                    return "redirect:/area/" + farm.getId();
                }
            }
            return "addFarm";
        }
        return "redirect:/login";
    }

    @GetMapping("/area/{id}/add")
    public String addOffer(@PathVariable int id,
                           @RequestParam(name = "huntingResource", required = false) String resource,
                           @RequestParam(name = "description", required = false) String description,
                           @RequestParam(name = "openingDate", required = false) String openingDate,
                           @RequestParam(name = "closingDate", required = false) String closingDate,
                           @RequestParam(name = "eventCost", required = false) Long eventCost,
                           @RequestParam(name = "guiding", required = false) Long guiding,
                           @RequestParam(name = "method", required = false) Long method, Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        if (session.isHunter()) {
            return "redirect:/";
        }
        HuntingFarm farm = huntingFarmRep.findById((long) id).orElse(null);
        if (farm == null) {
            return "redirect:/";
        }
        if (session.isOwner() && farm.getUserId() != session.getId()) {
            return "redirect:/";
        }
        if (resource != null && !resource.isEmpty() && openingDate != null && !openingDate.isEmpty() &&
                closingDate != null && !closingDate.isEmpty() && method != null && eventCost != null && guiding != null) {
            List<HuntingResources> resourcesList = resourcesRep.findByName(resource);
            HuntingResources huntingResources;
            if (resourcesList.isEmpty()) {
                huntingResources = resourcesRep.save(new HuntingResources(-1, resource));
            } else {
                huntingResources = resourcesList.get(0);
            }
            HuntingOffer offer = new HuntingOffer(-1, id, huntingResources.getId(), LocalDate.parse(openingDate),
                    LocalDate.parse(closingDate), method.intValue(), eventCost.intValue(), guiding.intValue(), description,
                    -1, null, null, null);
            offer = offerRep.save(offer);
            return "redirect:/area/" + offer.getFarmId();
        }

        model.addAttribute("id", id);
        return "addOffer";

    }

    @GetMapping("/area/{id}")
    public String placeInfo(@PathVariable int id,
                            @RequestParam(name = "huntingResource", required = false) String huntingResource,
                            @RequestParam(name = "checkInDate", required = false) String checkInDate,
                            @RequestParam(name = "leaveDate", required = false) String leaveDate,
                            @RequestParam(name = "guests", required = false) Long guests,
                            @RequestParam(name = "hunters", required = false) Long hunters,
                            @RequestParam(name = "guiding", required = false) String guiding,
                            @RequestParam(name = "noDate", required = false) boolean noDate,
                            @RequestParam(name = "method", required = false) String method, Model model) {

        if (guests == null || guests < 0L) {
            guests = 0L;
        }
        if (hunters == null || hunters < 0L) {
            hunters = 1L;
        }
        short quantity = (short) (guests + hunters);
        LocalDate checkIn;
        if (checkInDate == null || checkInDate.isEmpty()) {
            checkIn = LocalDate.MIN;
        } else {
            checkIn = LocalDate.parse(checkInDate);
        }
        LocalDate leave;
        if (leaveDate == null || leaveDate.isEmpty()) {
            leave = LocalDate.MIN;
        } else {
            leave = LocalDate.parse(leaveDate);
        }
        if (noDate) {
            checkIn = LocalDate.MIN;
            leave = LocalDate.MIN;
        }
        if (method == null || method.isEmpty()) {
            method = "0";
        }
        if (guiding == null || guiding.isEmpty()) {
            guiding = "0";
        }
        int guidingInt = Integer.parseInt(guiding);
        int methodInt = Integer.parseInt(method);


        if (checkIn.isAfter(leave)) {
            leave = LocalDate.MIN;
            checkIn = LocalDate.MIN;
        }

        HuntingFarm farm = huntingFarmRep.findById((long) id).get();
        farm.setRegionName(regionRep.findById((long) farm.getRegionCode()).get().getName());
        farm.setMunicipalDistrictName(districtRep.findById((long) farm.getMunicipalDistrictId()).get().getName());
        farm.setCompanyStr(companyNameRep.findById((long) farm.getCompanyId()).get().getName());

        int hotelPrice = 0;
        if (farm.isHotel()) {
            hotelPrice += quantity * farm.getAccommodationCost() * DAYS.between(checkIn, leave);
        }

        List<HuntingOffer> offers = offerRep.findByFarmId(id);

        List<HuntingOffer> findOffers = new ArrayList<>();

        for (HuntingOffer offer : offers) {
            boolean free = false;
            if (checkIn.isEqual(LocalDate.MIN) || leave.isEqual(LocalDate.MIN) || !checkIn.isAfter(offer.getOpeningDate()) && !leave.isBefore(offer.getClosingDate())) {
                int resource = resourcesRep.getOneByName(huntingResource);
                if (resource == -1 || resource == offer.getResourcesTypeId()) {
                    if (guidingInt == 0 || offer.getGuidingPreferenceId() == guidingInt || offer.getGuidingPreferenceId() == 0) {
                        if (methodInt == 0 || offer.getMethodId() == methodInt || offer.getMethodId() == 0) {
                            List<Booking> bookings = bookingRep.findByOfferId(offer.getId());
                            free = true;
                            if (!bookings.isEmpty()) {
                                for (Booking booking :
                                        bookings) {
                                    if (checkIn != LocalDate.MIN || leave != LocalDate.MIN) {
                                        if (!(booking.getLeave().isBefore(checkIn) || booking.getCheckIn().isAfter(leave))) {
                                            free = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
            if (free) {
                List<ResourcesCost> resourcesCosts = resourcesCostRep.findByOfferId(offer.getId());
                int minResourcesCost = Integer.MAX_VALUE;
                for (int j = 0; j < resourcesCosts.size(); j++) {
                    if (minResourcesCost > resourcesCosts.get(0).getCost()) {
                        minResourcesCost = resourcesCosts.get(0).getCost();
                        break;
                    }
                }
                if (minResourcesCost == Integer.MAX_VALUE) {
                    minResourcesCost = 0;
                }

                offer.setGuidingPreferenceName(guidingArr[offer.getGuidingPreferenceId()]);
                offer.setMethodName(methodArr[offer.getMethodId()]);
                offer.setResourcesTypeName(resourcesRep.findById((long) offer.getResourcesTypeId()).get().getName());
                offer.setMinCost(minResourcesCost + offer.getEventCost());
                findOffers.add(offer);
            }
        }
        List<Booking> bookings = bookingRep.findByFarmId(farm.getId());
        List<Booking> comments = new ArrayList<>();
        for (Booking booking :
                bookings) {
            if (booking.getReview() != null && !booking.getReview().isEmpty()) {
                comments.add(booking);
            }
        }
        for (Booking comment :
                comments) {
            comment.setImageLinks(imageLinkRep.findAllByOwnerId(comment.getId()));
            User user = userRep.findById((long) comment.getUserId()).get();
            String userName = userNameRep.findById((long) user.getNameId()).get().getName();
            comment.setUserName(user.getLastLame()+ " " + userName);
        }
        User user = userRep.findById(Long.valueOf(farm.getUserId())).get();
        String userName = userNameRep.findById((long) user.getNameId()).get().getName();
        user.setNameSrt(userName);

        model.addAttribute("farm", farm);
        model.addAttribute("user", user);
        model.addAttribute("images", imageLinkRep.findAllByOwnerId(farm.getId()));
        model.addAttribute("offers", findOffers);
        model.addAttribute("hotelPrice", hotelPrice);
        model.addAttribute("checkInAtr", checkIn.toString());
        model.addAttribute("leaveAtr", leave.toString());
        model.addAttribute("comments", comments);
        boolean thisOwnerOrAdmin = false;
        boolean sessionNotNull;
        if (session != null) {
            sessionNotNull = true;
            if (session.isAdmin()){
                thisOwnerOrAdmin = true;
            }
            if (session.isOwner() && session.getId() == farm.getUserId()) {
                thisOwnerOrAdmin = true;
            }
        } else {
            sessionNotNull = false;
        }
        model.addAttribute("thisOwnerOrAdmin", thisOwnerOrAdmin);
        model.addAttribute("sessionNotNull", sessionNotNull);
        return "farmInfo";
    }


    @GetMapping("/area/{id}/delete")
    public String deletePlace(@PathVariable Long id,
                              @RequestParam(name = "checkbox", required = false) boolean checkbox,
                              Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        if (session.isHunter()) {
            return "redirect:/";
        }
        HuntingFarm farm = huntingFarmRep.findById(id).orElse(null);
        if (farm == null) {
            return "redirect:/";
        }
        if (session.isOwner() && farm.getUserId() != session.getId()) {
            return "redirect:/";
        }
        if (checkbox) {
            model.addAttribute("id", id.intValue());
            huntingFarmRep.deleteById(id);
            List<ImageLink> imageLinks = imageLinkRep.findAllByOwnerId(id.intValue());
            for (ImageLink imageLink :
                    imageLinks) {
                imageRepository.deleteById((long) imageLink.getId());
            }
            return "redirect:/";
        }
        return "deleteFarm";
    }


    @GetMapping("/area/{farmId}/{offerId}/{checkInDate}/{leaveDate}")
    public String offerInfo(@PathVariable Long farmId,
                            @PathVariable Long offerId,
                            @PathVariable String checkInDate,
                            @PathVariable String leaveDate,
                            @RequestParam(name = "guests", required = false) Long guests,
                            @RequestParam(name = "book", required = false) boolean book,
                            @RequestParam(name = "hunters", required = false) Long hunters,
                            @RequestParam(name = "description", required = false) String description,
                            @RequestParam(name = "resId", required = false) String resourceId,
                            @RequestParam(name = "hotel", required = false) boolean hotel,
                            Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        HuntingFarm farm = huntingFarmRep.findById(farmId).orElse(null);
        HuntingOffer offer = offerRep.findById(offerId).orElse(null);

        if (offer != null && farm != null) {

            String dates;
            String minDates = LocalDate.MIN.toString();
            if (checkInDate.equals(minDates) || leaveDate.equals(minDates)) {
                dates = "Предпочтительная дата охоты не указана\n";
                checkInDate = "0001-01-01";
                leaveDate = "0001-01-01";
            } else {
                dates = "Дата заезда: " + checkInDate + "\n" +
                        "Дата отъезда: " + leaveDate + "\n";
            }
            if (guests != null && hunters != null && book) {
                String resourceName;
                if (resourceId == null) {
                    resourceId = "-1";
                }
                if (Long.parseLong(resourceId) != -1L) {
                    ResourcesCost resourcesCost = resourcesCostRep.findById(Long.parseLong(resourceId)).get();
                    resourceName =
                            "Предпочитаемый трофей " + resourcesCost.getTrophyValue() + "\n" +
                                    "Стоимость трофея " + resourcesCost.getCost() + "\n";
                } else {
                    resourceName = "Предпочитаемый трофей не указан \n";
                }
                String hotelInfo;
                if (hotel) {
                    hotelInfo = "Необходима гостиница";
                } else {
                    hotelInfo = "Гостиница не нужна";
                }
                if (description == null || !description.isEmpty()) {
                    description = "не указаны";
                }

                User user = userRep.findById(Long.valueOf(farm.getUserId())).get();
                String userName = userNameRep.findById((long) user.getNameId()).get().getName();

                String information =
                        "   " + farm.getName() + "\n" +
                                "Охота на " + resourcesRep.findById((long) offer.getResourcesTypeId()).get().getName() + "\n" +
                                resourceName +
                                "Регион: " + regionRep.findById((long) farm.getRegionCode()).get().getName() + "\n" +
                                "Муниципальный район: " + districtRep.findById((long) farm.getMunicipalDistrictId()).get().getName() + "\n" +
                                "Координаты лагеря в десятичном формате:" + "\n" +
                                "   широта — " + farm.getFirstCord() + " долгота — " + farm.getSecondCord() + "\n" +
                                "Дата открытия охоты: " + offer.getOpeningDate().toString() + "\n" +
                                "Дата закрытия охоты: " + offer.getClosingDate().toString() + "\n" +
                                "Директор охотхозяйства: " + user.getLastLame() + " " + user.getPatronymic() + " " + userName + "\n" +
                                "Номер телефона " + user.getPhoneNumber() + "\n" +
                                "Email " + user.getEmail() +
                                methodArr[offer.getMethodId()] + "\n" +
                                guidingArr[offer.getGuidingPreferenceId()] + "\n" +
                                "Стоимость организации охоты: " + offer.getEventCost() + "\n" +
                                hotelInfo + "\n" + "\n" +
                                dates +
                                "Колличество охотников: " + hunters + "\n" +
                                "Колличество гостей: " + guests + "\n" +
                                "Пожелания: " + description + "\n";
                Booking booking = new Booking(-1, farmId, session.getId(), offerId, information, Timestamp.valueOf(LocalDateTime.now()), null,
                        LocalDate.parse(checkInDate), LocalDate.parse(leaveDate), null, null, null);
                //userId
                booking = bookingRep.save(booking);
                return "redirect:/booking/" + booking.getId();

            }

            farm.setRegionName(regionRep.findById((long) farm.getRegionCode()).get().getName());
            farm.setMunicipalDistrictName(districtRep.findById((long) farm.getMunicipalDistrictId()).get().getName());


            model.addAttribute("farm", farm);
            offer.setGuidingPreferenceName(guidingArr[offer.getGuidingPreferenceId()]);
            offer.setMethodName(methodArr[offer.getMethodId()]);
            offer.setResourcesTypeName(resourcesRep.findById((long) offer.getResourcesTypeId()).get().getName());
            model.addAttribute("offer", offer);
            model.addAttribute("resources", resourcesCostRep.findByOfferId(offerId.intValue()));
            model.addAttribute("dates", dates);

            boolean thisOwnerOrAdmin = false;
            boolean sessionNotNull = false;
            if (session != null) {
                sessionNotNull = true;
                if (session.isAdmin()){
                    thisOwnerOrAdmin = true;
                }
                if (session.isOwner() && session.getId() == farm.getUserId()) {
                    thisOwnerOrAdmin = true;
                }
            }
            model.addAttribute("thisOwnerOrAdmin", thisOwnerOrAdmin);
            model.addAttribute("sessionNotNull", sessionNotNull);

            return "offerInfo";
        }
        return "redirect:/";
    }

    @GetMapping("/area/{farmId}/{offerId}/delete")
    public String deletePlace(@PathVariable Long farmId,
                              @PathVariable Long offerId,
                              @RequestParam(name = "checkbox", required = false) boolean checkbox,
                              Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        if (session.isHunter()) {
            return "redirect:/";
        }
        HuntingFarm farm = huntingFarmRep.findById(farmId).orElse(null);
        if (farm == null) {
            return "redirect:/";
        }
        if (session.isOwner() && farm.getUserId() != session.getId()) {
            return "redirect:/";
        }
        if (checkbox) {
            offerRep.deleteById(offerId);
            return "redirect:/area/" + farmId;
        }
        model.addAttribute("farmId", farmId.intValue());
        model.addAttribute("offerId", offerId.intValue());
        return "deleteOffer";
    }

    @GetMapping("/area/{farmId}/{offerId}/addCost")
    public String addCost(@PathVariable Long farmId,
                          @PathVariable Long offerId,
                          @RequestParam(name = "description", required = false) String description,
                          @RequestParam(name = "cost", required = false) Long cost,
                          Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        if (session.isHunter()) {
            return "redirect:/";
        }
        HuntingFarm farm = huntingFarmRep.findById(farmId).orElse(null);
        if (farm == null) {
            return "redirect:/";
        }
        if (session.isOwner() && farm.getUserId() != session.getId()) {
            return "redirect:/";
        }
        if (description != null && cost != null) {
            resourcesCostRep.save(new ResourcesCost(-1, offerId.intValue(), description, cost.intValue()));
            return "redirect:/area/" + farmId;
        }
        model.addAttribute("farmId", farmId.intValue());
        model.addAttribute("offerId", offerId.intValue());
        return "addCost";
    }

    @GetMapping("/area/{farmId}/{offerId}/{costId}/delete")
    public String deleteCost(@PathVariable Long farmId,
                             @PathVariable Long costId) {
        if (session == null) {
            return "redirect:/login";
        }
        if (session.isHunter()) {
            return "redirect:/";
        }
        HuntingFarm farm = huntingFarmRep.findById(farmId).orElse(null);
        if (farm == null) {
            return "redirect:/";
        }
        if (session.isOwner() && farm.getUserId() != session.getId()) {
            return "redirect:/";
        }
        resourcesCostRep.deleteById(costId);
        return "redirect:/area/" + farmId;
    }

    @GetMapping("/booking/{id}")
    public String bookingInfo(@PathVariable Long id,
                              @RequestParam(name = "newReview", required = false) String newReview,
                              Model model) {
        Booking booking = bookingRep.findById(id).get();
        if (session == null) {
            return "redirect:/login";
        }
        if (newReview != null && !newReview.isEmpty()) {
            booking.setReview(newReview);
        }
        booking = bookingRep.save(booking);
        String review = booking.getReview();
        if (review == null || review.isEmpty()) {
            review = "После охоты не забудте оставить отзыв";
        }
        String info = booking.getInformation();
        String checkIn = booking.getCheckIn().toString();
        String leave = booking.getLeave().toString();
        String dates;
        if (checkIn.equals("0001-01-01") || leave.equals("0001-01-01")) {
            dates = "Даты охоты не указаны\n";
        } else {
            dates = "Дата заезда: " + checkIn + "\n" +
                    "Дата отъезда: " + leave + "\n";
        }
        String bookingTime = booking.getTimestamp().toString();

        model.addAttribute("user", session);
        model.addAttribute("images", imageLinkRep.findAllByOwnerId(booking.getId()));
        model.addAttribute("bookingTime", bookingTime);
        model.addAttribute("dates", dates);
        model.addAttribute("review", review);
        model.addAttribute("info", info);
        model.addAttribute("id", id.toString());

        return "bookingInfo";


    }

    @GetMapping("/booking/{id}/delete")
    public String deleteBooking(@PathVariable Long id,
                                @RequestParam(name = "checkbox", required = false) boolean checkbox,
                                Model model) {
        Booking booking = bookingRep.findById(id).orElse(null);
        if (session == null) {
            return "redirect:/login";
        }
        if (booking == null || !session.isAdmin() && session.getId() != booking.getUserId()) {
            return "redirect:/";
        }
        if (checkbox) {
            bookingRep.deleteById(id);
            List<ImageLink> imageLinks = imageLinkRep.findAllByOwnerId(id.intValue());
            for (ImageLink imageLink :
                    imageLinks) {
                imageRepository.deleteById((long) imageLink.getId());
            }
            return "redirect:/";
        }
        model.addAttribute("id", id.toString());
        return "deleteBooking";
    }

    @GetMapping("/loadImage/{id}")
    public String loadImage(@PathVariable String id, Model model) {
        if (session == null) {
            return "redirect:/login";
        }
        model.addAttribute("id", id);
        return "addImage";
    }

    @PostMapping("/image/add/{id}")
    public String createImage(@PathVariable Long id,
                              @RequestParam("file") MultipartFile file) throws IOException {
        Image image = huntingFarmService.saveImage(file, id);
        if (image != null) {
            imageLinkRep.save(new ImageLink(image.getId().intValue(), image.getOwnerId().intValue()));
        } else {
            return "redirect:/";
        }

        if (id < 500000000) {
            return "redirect:/area/" + id;
        } else {
            if (id < 1000000000) {
                return "redirect:/personal";
            } else {
                return "redirect:/booking/" + id;
            }
        }

    }
}

