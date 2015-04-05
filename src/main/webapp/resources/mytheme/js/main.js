/**
 * namespace = Index
 */
var Index = {};
Index.stompClient1 = null;
Index.stompClient2 = null;
Index.stompClient3 = null;

Index.socket1 = null;
Index.socket2 = null;
Index.socket3 = null;

$(document).ready(function() {
    Index.initEventListeners();
    // auto connect
    Index.connect();
    Index.startBannerLoop();
});

/**
 * endless banner loop
 */
Index.startBannerLoop = function startBannerLoop(){
    Index.bannerFirstSlide();
}

Index.bannerFirstSlide = function bannerFirstSlide(){
    $("#logoContainer").fadeOut(500,function(){
        $("#bonusTextContainer").removeClass("displayNone").addClass("displayBlock");
        $("#bonusText1").slideDown(1000, function(){
            $("#buttonLink").fadeIn();
        });
        setTimeout(Index.bannerSecondSlide(),5000);
    });
}

Index.bannerSecondSlide = function bannerSecondSlide(){
    $("#bonusText1").fadeOut(1000,function(){
        $("#bonusText2").slideDown(1000,function(){
            setTimeout(Index.bannerThirdSlide(),8000);
        });
    });
}

Index.bannerThirdSlide = function bannerThirdSlide(){
    $("#buttonLink").fadeOut();
    setTimeout(function(){
        $("#bonusText1").fadeOut();
        $("#bonusText2").fadeOut();
    },500);
    setTimeout(function(){$("#bonusTextContainer").addClass("displayNone");},1000);
    setTimeout(function(){
        $("#logoContainer").fadeIn(1000,function(){
            setTimeout(Index.bannerFirstSlide(),5000);
        });
    },2000);
}

Index.startInitialProcess = function startInitialProcess () {

    $.ajax({
        type: 'GET',
        url: '/init',
        dataType: 'JSON',
        success: function (response) {
            var data = response.data;
            data.each( function(index){
                $('#console').html(this);
            });
        }
    });
}

/**
 * initialize button events
 */
Index.initEventListeners = function initEventListeners() {
  //  $("#connect").onclick = function(event) {  }
  //  $("#disconnect").onclick = function(event) { Index.disconnect(); }
  //  $("#formButton").onclick = function(event) { Index.formButtonClickListener(); }
    //$("hello").onclick = function(event) { wstool.write("Hello There"); return false; }
}


/**
 * form button click event listener
 *
 *  todo bu send mesajlarinin header ve icerigini bos birakinca serverdaki mapper anlamiyo
 */
Index.getDataButtonClickListener = function getDataButtonClickListener(){

    var x = $("#x").val();
    var y = $("#y").val();
    var quote = {X: x, Y: y};
    Index.stompClient3.send("/all", {}, JSON.stringify(quote));
}

/**
 * web socket subscribers
 */
Index.connect = function connect() {
    Index.socket1 = new SockJS('/random');
    Index.stompClient1 = Stomp.over(Index.socket1);
    Index.stompClient1.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        Index.stompClient1.subscribe('/topic/random', function(randomEvent){
            Index.randomTopicEventListener(randomEvent)
        });

    });
    Index.socket2 = new SockJS('/xy');
    Index.stompClient2 = Stomp.over(Index.socket2);
    Index.stompClient2.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        Index.stompClient2.subscribe('/topic/xy', function(xyEvent){
            Index.xyTopicEventListener(xyEvent)
        });

    });

    Index.socket3 = new SockJS('/all');
    Index.stompClient3 = Stomp.over(Index.socket3);
    Index.stompClient3.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        Index.stompClient3.subscribe('/topic/all', function(allEvent){
            Index.allTopicEventListener(allEvent)
        });

    });
    Index.setConnected();
}


Index.setConnected = function setDisConnected(){
    $("#connect").prop( "disabled", true );
    $("#disconnect").prop( "disabled", false );
    $("#formButton").prop( "disabled", false );
};
Index.setDisConnected = function setConnected(){
    $("#connect").prop( "disabled", false );
    $("#disconnect").prop( "disabled", true );
    $("#formButoon").prop( "disabled", true );
};


Index.xyTopicEventListener = function xyTopicEventListener(message){
    //alert(message.body);
    var quote = message.body+"";


    var variables = quote.split(",");
    var rL = variables[0];
    var rS = variables[1];
    var y = variables[2];
    $("#rLong").val(rL);
    $("#rString").val(rS);
    $("#y").val(y);


    //Index.showResult(JSON.parse(randomEvent.body).result)
}

Index.randomTopicEventListener = function randomTopicEventListener(message){
    alert(message.body);
}

Index.allTopicEventListener = function randomTopicEventListener(message){
    alert(message.body);
}

Index.disconnect = function disconnect() {
    Index.stompClient1.disconnect();
    Index.stompClient2.disconnect();
    Index.stompClient3.disconnect();
    console.log("Disconnected");
    Index.setDisConnected();
    console.log("disconnect-cik");
}




/*
Index.sendNum = function sendNum() {
    var num1 = document.getElementById('num1').value;
    var num2 = document.getElementById('num2').value;
    stompClient.send("/calcApp/add", {}, JSON.stringify({ 'num1': num1, 'num2': num2 }));
}
*/

