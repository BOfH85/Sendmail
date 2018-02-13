##################################
#
#     Script zum Senden mit Java
#
# (c) 09.02.2018 Oliver Jung
##################################


$SendmailPath = "C:\Sendmail\Sendmail.bat"


#Befüllbare Variablen
$From="-from ""testat@msgmediaservice.de"""
$to="-to ""o.jung@msgmediaservice.de"""
$cc="-cc ' '"
$bcc="-bcc ' ' "
$Subject="-subject ""TestMail über Java"""
$Body="-body ""Das ist nur ein kleiner Mailtest"""


$env:message = $From +" "+$to +" "+$cc +" "+$bcc +" "+$Subject +" "+$Body


$SendMailMessage =$SendmailPath+" "+$env:message

Invoke-Expression $SendMailMessage
