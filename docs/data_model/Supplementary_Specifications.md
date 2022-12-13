# Supplementary Specifications

## **Functional Requirements**
**FURPS**

**FUNCTIONALITIES**

_Login and Logout_
_Scrum Recordkeeping_
_Legacy Data Input_ 

## **Non-functional Requirements**

**FURPS**

**USABILITY:**
	_SESSION TIMEOUT_: The system detects the activity of the user, closing the session of the user when not activity is detected in more than 30 minutes.
	_SAVED PASSWORDS_: The passwords should not be saved to the user’s database, in order not to allow others to access the account unduly. 
	_LANGUAGE AND LOCATION_: Options will allow for the formatting of text, dates and decimals to be configured according to user preferences and language.
	
**RELIABILITY**
	_LOGGING_: Generating reports on all user operations, system messages (warnings or errors), saved in log files and identified with the following levels: debug, info, warn, error.
**PERFORMANCE**
	_RESPONSIVE LAYOUT_: The layout design developed for the user interface must be adaptable to different devices automatically. The layout will be able to adapt correctly to the screens of either a mobile phone or a laptop, display the information with no loss of resolution and/or data, to maximise the usability and user experience of the application.

Session Timeout > Usability/Performance (?)
Logging > Reliability 
Saved Passwords > Usability
Language and Location > Usability
Responsive Layout > Performance
