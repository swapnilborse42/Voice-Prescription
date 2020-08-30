# Voice-Prescription
The idea of the application which allows doctors to generate patient report using voice by using some keywords.The generated pdf can be share to patients over the social media..for extra security the generated report is in the password protected format. Doctors is also able to search perticular patient reports using his/her mobile number. 

# Modules-in-Project <br>
1.Generate Patient report using voice by dictecting through keywords.<br>
2.Generated Report must be password protected by user mobile number(suggested by HIPPA rules)-Default password-1234<br>
3.view / search all patient report and search patient history using patient mobile number<br>
4.Generated report can be share on social media like Whatsapp or Gmail.<br>

# Technology <br>
*Firebase- To store Login/Logup (Doctors Authentication data ) and store all patients reports<br>
*Google API- TO convert voice into text<br>
*Android SDK<br>
*PDFDocument class - To generate PDF in android devices<br>
*itextPDF- TO password protect PDF <br>

# Login/Logup 
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/a.jpeg" width="300" height="500"/>          <img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/b.jpeg" width="300" height="500"/>
-This module is connected with firebase to authenticate the doctors

# Home Activity Module
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/c1.jpeg" width="300" height="500"/>                <img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/c2.jpeg" width="300" height="500"/> 
- This activity is created using Gridlayout and Srcollable view

# Search Module
search bar and scrollable view <br>
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/d.jpeg" width="300" height="500"/>  <img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/e.jpeg" width="300" height="500"/> <br>
-In search  Doctor can search for patient previous report using mobile number. <br>
-For all patients recored in list view Doctor can see (for every entry) Mobile number ,date and time when report generated, symtoms and comments. <br>
-By tapping on Patient record pdf is downloaded from firebase database and open in external pdf viewer automatically and Thus internet connectionis must.<br>
 
# Patient Pdf-generation moulde
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/f.jpeg" width="300" height="500"/> <br>
-Once tap on mic icon doctor have to dictect the patient info after the KEYWORDS like name symptoms, Diagnosis, prescription, Advice <br>
E.g - name Swapnil Symptoms cold and fever Diagnosis backpain Prescription Crosin Advice take steam <br>
After that all the info after keyword will be reflected inside sample report as below (If there is a mistake while speking, Doctor can also edit the the report manually) <br>
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/g.jpeg" width="300" height="500"/>&nbsp<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/h.jpeg" width="300" height="500"/> <br>
-On Click GENERATE REPORT Report is generated and stored under mobile phone storage. <br>
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/i.jpeg" width="300" height="500"/> <img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/j.jpeg" width="300" height="500"/>
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/k.jpeg" width="300" height="500"/>
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/l.jpeg" width="300" height="500"/> <br>
-HEre DOctor can add any Special Comment for a perticular patient as shown and once Click on UPLOAD the same pdf with comment is stored uner firebase database and also Doctor get an Option to share password protected pdf report to various social media plateforms  <br>

# Prescription Module
HEre Doctor will able to see common prescription for for Common deseses.

# Report Module
This module is same as search module but insted of search pannel Doctor able to see all report day wise(current data first)

# Family History of Patient (SUGGESSTION)
NOT Completed module-- HEre doctor is supposed to see all FAMILY history of perticular patient.

# MY PRofile module 
<img src="https://github.com/swapnilborse42/Voice-Prescription/blob/master/voice%20prescription%20screenshots/m.jpeg" width="300" height="500"/> <br>
-Doctor can see his/her PRofile and also able to logout.














 
