const carousel = document.getElementsByClassName('event-carousel')[0];
const carouselSuperWrapper = document.getElementsByClassName('event-carousel-card-super-wrapper')[0];
let wrapper = document.getElementsByClassName('event-carousel-card-wrapper')[0];
let cards = Array.from(document.getElementsByClassName('event-carousel__card'));

wrapper.style.display = 'none';

let widthSuper = parseInt(carouselSuperWrapper.offsetWidth);
let widthCard = 200;
let count = (widthSuper - (widthSuper % widthCard)) / widthCard;
let margins = (widthSuper % widthCard) / (Math.min(count, cards.length) + 1);

carouselSuperWrapper.style.width = `${carouselSuperWrapper.offsetWidth}px`;

cards.forEach(card => {
    card.style.margin = `0 ${margins / 2}px`;
});
if (cards.length !== 0) {
    cards[0].style.marginLeft =  `${margins}px`;
    cards[cards.length - 1].style.marginRight = `${margins}px`
}

wrapper.style.display = 'flex';


let cardsInLeft = 0; // положение ленты прокрутки

const arrowLeft = document.getElementsByClassName('event-carousel__arrow_left')[0];
const arrowRight = document.getElementsByClassName('event-carousel__arrow_right')[0];

function toLeft() {
    if (cardsInLeft !== Math.max(cards.length - count, 0)) {
        cardsInLeft++;
        let pxToTransform = cardsInLeft * (margins + widthCard);
        wrapper.style.transform = `translateX(${pxToTransform * (-1)}px)`;
        arrowRight.style.opacity = '1';
    }
    if (cardsInLeft === Math.max(cards.length - count, 0)) {
        arrowLeft.style.opacity = '0.5'
    }
}

function toRight() {
    if (cardsInLeft !== 0) {
        cardsInLeft--;
        let pxToTransform = cardsInLeft * (margins + widthCard);
        wrapper.style.transform = `translateX(${pxToTransform * (-1)}px)`;
        arrowLeft.style.opacity = '1';
    }
    if (cardsInLeft === 0) {
        arrowRight.style.opacity = '0.5'
    }
}

arrowLeft.addEventListener('click', toLeft);
arrowRight.addEventListener('click', toRight);

arrowRight.style.opacity = '0.5';
if (cardsInLeft === Math.max(cards.length - count, 0)) {
    arrowLeft.style.opacity = '0.5'
}

