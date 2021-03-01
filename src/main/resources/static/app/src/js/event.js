import $ from 'jquery';
import BoardWritePopup from './view/BoardWritePopup';

$('#reportWriteBtn').on('click', function(){

});

$('#boardWritePopupOpen').on('click', function() {
    console.log('실행체크');
    const boardWritePopup = new BoardWritePopup();
    boardWritePopup.render();
});

