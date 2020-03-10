const arrow = document.getElementsByClassName('arrow-to-top')[0];

arrow.style.display = 'none';

arrow.addEventListener('click', function () {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
});

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        arrow.style.display = "block";
        arrow.style.opacity = '0.6';
    } else {
        arrow.style.display = "none";
    }
}