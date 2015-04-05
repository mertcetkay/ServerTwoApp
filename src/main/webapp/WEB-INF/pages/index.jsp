<!DOCTYPE html>
<html>
<head>

    <link href="/resources/css/main.css" rel="stylesheet"/>
    <link href="/resources/css/styles.css" rel="stylesheet"/>
    <script src="/resources/js/jquery-2.1.3.min.js"></script>
    <script src="/resources/js/stomp.js"></script>
    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/sockjs.js"></script>
</head>
<body>
Server 2 Application

<div id="background" class="graphical-assets" >
        <div id="bonusTextContainer" >
            <div id="bonusText1">
                <h1>Swedens nicest</h1>
                <h2>Bonus!</h2>
            </div>
            <div id="bonusText2" class="displayNone">
                <h1>Deposit $10</h1>
                <h2>Get $50</h2>
            </div>

        </div>
        <div id="buttonLink" style="display: none;">
            <a href="https://www.comeon.com" target="_blank" class="ui-button size-60 color-primary">Join us today!</a>
        </div>
        <div id="logoContainer" style="display: none;">
            <div id="logo-en" class="ui-logo"></div>
        </div>
</div>


<form class="form" class="validateDontSubmit">
    <ul>
        <li>
            <label for="y">Y:</label>
            <input id="y" type="text"  disabled="disabled"/>
        </li>
        <li>
            <label for="rLong">Long:</label>
            <input id="rLong" type="text"  disabled="disabled"/>
        </li>
        <li>
            <label for="rString">String:</label>
            <input id="rString" type="text" disabled="disabled"/>
        </li>
    </ul>
</form>
</body>
</html>