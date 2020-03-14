function ellipsizeTextBox(className) {
    const el = document.getElementsByClassName(className)[0];
    const wordArray = el.innerHTML.split(' ');
    while(el.scrollHeight > el.offsetHeight) {
        wordArray.pop();
        el.innerHTML = wordArray.join(' ') + '...';
    }
}
ellipsizeTextBox('card-about');