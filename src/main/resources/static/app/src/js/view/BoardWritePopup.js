import $ from 'jquery';

export default class BoardWritePopup {
    constructor(props) {
        this.container = document.createElement('div');
        document.body.append(this.container);
    }

    render() {
        const template = '<div>BoardWritePopup 등장<div>';
        $(this.container).html(template);
    }
}