<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.aileci.speechcenter.serviceimpl.*,com.aileci.speechcenter.utils.*" %>
<html>
<body>
<form action="/leci-speech-center/speechcenter/getspeech" method="post" >
    <span>请输入将要转成语音的例句</span><input type="text" name="text"/>
    <span>请输入将要转成语音的音量</span><input type="text" name="volume"/>
    <span>请输入将要转成语音的速率</span><input type="text" name="rate"/>
    <span>请输入将要转成语音的格式</span><input type="text" name="fileType"/>
    <input type="submit" value="搞起来"/>
</form>
</body>
</html>
