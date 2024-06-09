$(document).ready(function() {
    $('#in').fadeIn(2000);

    // response1-markdownとresponse2-markdownの内容をマークダウンからHTMLに変換する
    const response1Markdown = $('#response1-markdown').text().trim();
    const response2Markdown = $('#response2-markdown').text().trim();

    // markedの使用
    if (typeof marked !== 'undefined') {
        $('#response1-markdown').html(marked.parse(response1Markdown));
        $('#response2-markdown').html(marked.parse(response2Markdown));
    } else {
        console.error("marked.js is not loaded properly.");
    }
});