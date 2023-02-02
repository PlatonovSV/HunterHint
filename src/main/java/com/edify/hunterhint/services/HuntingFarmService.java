package com.edify.hunterhint.services;

import com.edify.hunterhint.models.HuntingFarm;
import com.edify.hunterhint.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.edify.hunterhint.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@RequiredArgsConstructor
@Slf4j
@Service
public class HuntingFarmService {
    private final HuntingFarmRep huntingFarmRep;
    private final MunicipalDistrictRep municipalDistrictRep;
    private final RegionRep regionRep;
    private final HuntingResourcesRep resourcesRep;
    private final HuntingOfferRep offerRep;
    private final BookingRep bookingRep;
    private final ResourcesCostRep costRep;
    private final ImageRepository imageRepository;


    public List<HuntingFarm> findByRegionAndMd(String region, String municipalDistrict) {
        short code = -1;
        if (region != null && !region.isEmpty()) {
            List<Region> regions = regionRep.findByName(region);
            if (!regions.isEmpty()) {
                code = regions.get(0).getCode();
            } else {
                code = -2;
            }
        }
        int munDsId = -1;
        if (municipalDistrict != null && !municipalDistrict.isEmpty() && code != -1) {
            List<MunicipalDistrict> municipalDistricts = municipalDistrictRep.findByNameAndRegion(municipalDistrict, code);
            if (!municipalDistricts.isEmpty()) {
                munDsId = municipalDistricts.get(0).getId();
            } else {
                munDsId = -2;
            }
        }
        if (code == -2 || munDsId == -2) {
            return new ArrayList<>();
        }
        if (code == -1) {
            return huntingFarmRep.findAll();
        } else {
            if (munDsId == -1) {
                return huntingFarmRep.findByRegionCode(code);
            } else {
                return huntingFarmRep.findByRegionCodeAndMunicipalDistrictId(code, munDsId);
            }
        }
    }

    public List<HuntingFarm> bathAndHotelSearch(boolean needHotel, short quantity, boolean needBath, List<HuntingFarm> huntingFarms) {
        List<HuntingFarm> result = new ArrayList<>();
        boolean conformity = true;
        for (HuntingFarm huntingFarm : huntingFarms) {
            if (needHotel) {
                conformity = huntingFarm.isHotel();
                if (conformity) {
                    conformity = huntingFarm.getHotelCapacity() > quantity;
                }
            }
            if (needBath && conformity) {
                conformity = huntingFarm.isBath();
            }
            if (conformity) {
                result.add(huntingFarm);
            }
        }
        return result;
    }

    public HuntingFarm upload(HuntingFarm huntingFarm) {
        List<Region> regions = regionRep.findByName(huntingFarm.getRegionName());
        Region region;
        if (regions.isEmpty()) {
            regionRep.save(new Region((short) -1, huntingFarm.getRegionName()));
            region = regionRep.findByName(huntingFarm.getRegionName()).get(0);
        } else {
            region = regions.get(0);
        }
        List<MunicipalDistrict> municipalDistricts = municipalDistrictRep.findByNameAndRegion(huntingFarm.getMunicipalDistrictName(), region.getCode());
        MunicipalDistrict municipalDistrict;
        if (municipalDistricts.isEmpty()) {
            municipalDistrictRep.save(new MunicipalDistrict(-1, huntingFarm.getMunicipalDistrictName(), region.getCode()));
            municipalDistrict = municipalDistrictRep.findByNameAndRegion(huntingFarm.getMunicipalDistrictName(), region.getCode()).get(0);
        } else {
            municipalDistrict = municipalDistricts.get(0);
        }
        huntingFarm.setRegionCode(region.getCode());
        huntingFarm.setMunicipalDistrictId(municipalDistrict.getId());
        return huntingFarmRep.save(huntingFarm);
    }

    public Image saveImage(MultipartFile file, Long id) throws IOException {
        if (file != null && file.getSize() != 0) {
            Image image = new Image();
            image.setOwnerId(id);
            image.setId(-1L);
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            return imageRepository.save(image);
        }
        return null;
    }

    public List<HuntingFarm> findByDateAndCostAndOther(List<HuntingFarm> huntingFarms, LocalDate checkIn,
                                                       LocalDate leave, int numberOfClients,
                                                       boolean needHotel, String resourceType,
                                                       int maxCost, int guiding, int method) {
        List<HuntingFarm> result = new ArrayList<>();
        int peopleMultiDays = 0;
        if (needHotel) {
            peopleMultiDays += numberOfClients * DAYS.between(checkIn, leave);
        }

        if (!huntingFarms.isEmpty()) {
            for (HuntingFarm farm :
                    huntingFarms) {
                boolean isMatch = false;
                boolean free = true;
                farm.setMinCost(Integer.MAX_VALUE);
                List<HuntingOffer> huntingOffers = offerRep.findByFarmId(farm.getId());
                int resourceId;
                if (resourceType == null || resourceType.isEmpty()) {
                    resourceId = 0;
                } else {
                    resourceId = resourcesRep.getOneByName(resourceType);
                }
                if (!huntingOffers.isEmpty()) {
                    for (HuntingOffer offer :
                            huntingOffers) {
                        if ((offer.getResourcesTypeId() == resourceId || resourceId == 0) && resourceId != -1) {
                            if (guiding == 0 || offer.getGuidingPreferenceId() == guiding) {
                                if (method == 0 || offer.getMethodId() == method) {
                                    if (checkIn.isEqual(LocalDate.MIN) || leave.isEqual(LocalDate.MIN) || !checkIn.isAfter(offer.getOpeningDate()) && !leave.isBefore(offer.getClosingDate())) {
                                        isMatch = true;
                                    }
                                }
                            }
                            if (isMatch) {
                                List<Booking> bookings = bookingRep.findByOfferId(offer.getId());
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

                                if (free) {
                                    int cost = peopleMultiDays * farm.getAccommodationCost();
                                    cost += offer.getEventCost();
                                    List<ResourcesCost> costs = costRep.findByOfferId(offer.getId());
                                    if (!costs.isEmpty()) {
                                        int minTrophyValue = Integer.MAX_VALUE;
                                        for (ResourcesCost resourcesCost :
                                                costs) {
                                            if (resourcesCost.getCost() < minTrophyValue) {
                                                minTrophyValue = resourcesCost.getCost();
                                            }
                                        }
                                        cost += minTrophyValue;
                                    }
                                    if (farm.getMinCost() > cost) {
                                        farm.setMinCost(cost);
                                    }
                                }
                            }
                        }
                    }
                    if (farm.getMinCost() <= maxCost) {
                        result.add(farm);
                    }
                }

            }
        }
        return result;
    }
}
