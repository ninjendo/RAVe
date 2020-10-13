# RAVe
RAVe Real Estate Lead Funnelling System

RAVe is a system that assist RE investors on tracking high-equity properties in the market. Its current focus is to track the properties being sold in HudHomeStore.com. It grabs the list of HUD homes from the site, parses it, and grabs the information on properties from Zillow. Data is then stored in the cache server. It can send out various notification to user based on desired filter e.g. price reduction, % equity reached, back in market, etc.

## Technologies:
- JAVA 8
- Springboot 1.4.3
- Spring REST
- Swagger UI
- Zillow WS-API
- Hazelcast 3.6.7
- Google API

## In the pipeline:
- migrate project in AWS
- unit tests
- add scheduled tasks (daily lead download, process accepted bids)
- develop UI
- cache backup in DB
- use docker for deployment
- screen scrape zillow and redfin to avoid 1000 requests limit per day from zillow
- better filter mechanism for preforeclosure leads

## How to Run:
1) install application using maven
2) Run the cache server first.  Open rave-cache and run com.ninjendo.rave.cache.CacheServerApplication.java
3) Run the main application: com.ninjendo.rave.RaveApplication.java
- the main app will connect to the cache server and run the scheduler. The scheduler (com.ninjendo.rave.service.ZillowService@downloadHudLeads) will download the list of properties from hudhomestore.com, retrieve the property details from zillow.com, then save it in the cache.  
