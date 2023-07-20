## Fitbitapi

Fitbit API App Inventor Extension: Seamlessly integrate Fitbit data into Android apps. Track steps, heart rate, sleep, and more for personalized health apps. Simplify fitness tracking and enhance user experience.

## Table of Contents

- [First steps](#installation)
- [Installation](#usage)
- [Usage](#contributing)


## First steps

1. Fisrt login [here](https://dev.fitbit.com/login) to your account Fitbit.
2. Go to [apps](https://dev.fitbit.com/apps) and register new one.
3. ![ref new app referecnce](https://github.com/Ow1omir/AI-fitbitapi/blob/main/git%20img/newapppic.jpg?raw=true)
4. After, visit my [python script](https://trinket.io/python3/c449f80cba), enter client ID, secret key, and redirect URL, run the script, follow generated URL in console, allow requirements, then on localhost, find the final authorization code in the URL. Paste it in the console to get your API key.

   if url isn't working:
   ````
    import requests
    
    CLIENT_ID = "" # Enter your Client ID
    CLIENT_SECRET = "" # Enter your Client Secret Key
    REDIRECT_URL = "https://localhost" # Enter you Redirect URL
    
    # Step 1: Get authorization code
    authorization_url = f"https://www.fitbit.com/oauth2/authorize?response_type=code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URL}&scope=activity%20nutrition%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight&expires_in=604800"
    print("Please authorize the application by visiting the following URL:")
    print(authorization_url)
    authorization_code = input("Enter the authorization code: ")
    
    # Step 2: Exchange authorization code for access token
    token_url = "https://api.fitbit.com/oauth2/token"
    payload = {
        "client_id": CLIENT_ID,
        "grant_type": "authorization_code",
        "redirect_uri": REDIRECT_URL,
        "code": authorization_code,
    }
    response = requests.post(token_url, auth=(CLIENT_ID, CLIENT_SECRET), data=payload)
    response_json = response.json()
    access_token = response_json["access_token"]
    print("Access token:", access_token)
   ````


## Installation

To use extansion you need to dowload compiled [file](https://github.com/Ow1omir/AI-fitbitapi/tree/main/out) from directiory "[out](https://github.com/Ow1omir/AI-fitbitapi/tree/main/out)" and import it in App Inventor.

## Usage

![Usage gif](https://github.com/Ow1omir/AI-fitbitapi/blob/main/git%20img/usageGif.gif?raw=true)

