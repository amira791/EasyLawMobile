Here all steps to run this Mobile App :
* Ngrok Installation 
   - install ngrok from this link https://ngrok.com/download
   - run you server and get the port number (for example 8000)
   - in cmd navigate to the folder where ngrok.exe exist
   - run this command " ngrok http 8000 " you will get a free public url to host the server
   - add this url to Const.kt in kotlin project
   - add it also in ALLOWED_HOSTS (settings.py) in django project
* Elastic search configuration 
   - run elastic search in your cmd by this command " elasticsearch "
* Running the app :
   - install an emulator and confirm that is connected to the internet
   - or you can use your  phone in developement mode 
