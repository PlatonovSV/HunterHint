class Slides {
  constructor(elementName) {
    this.elementName = elementName
    this.showSlides(1)
  }

  slideIndex = 1;

  showSlides(n) {
    let i;
    const slides = document.getElementsByClassName(this.elementName);
    if (n > slides.length) {
      this.slideIndex = 1
    }
    if (n < 1) {
      this.slideIndex = slides.length
    }
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    slides[this.slideIndex - 1].style.display = "block";
  }

  plusSlides(n) {
    this.showSlides(this.slideIndex += n);
  }

}

const mainSl = new Slides("mySlides")
const comSl1 = new Slides("mySlidesComment1")
const comSl2 = new Slides("mySlidesComment2")
const comSl3 = new Slides("mySlidesComment3")
