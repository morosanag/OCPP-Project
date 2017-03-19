# OCPP-Project
Management system for electrical charging stations

## System architecture
<p align="center">
  <img src="https://raw.githubusercontent.com/morosanag/OCPP-Project/master/architecure.png"  width="640" height="480"/>
</p>

The components of the systems are:
1. Database - in which the data is stored
2. Business Logic Controller (BLC)- processes the requests from the WebSocket Interface Controller and updates the database
3. WebSocket Interface Controller (WIC) - a bridge between the charging stations
and administrator which also calls interface of BLC
4. SOAP Interface Controller (SIC) - Endpoint for charging stations using SOAP over HTTP, converts SOAP to JSON messages and pass them to BIC
5. Charging station (WebSocket Interface/SOAP Interface)
6. Administrator - control the charging station through WIC

## Papers
[Electric Mobility in Green and Smart Cities - OCPP](https://drive.google.com/file/d/0BzI7XjAgDAvhRDA1YUFYbnJ4czA/view?usp=sharing)
[Electric Mobility in Green and Smart Cities -OCPP security](https://drive.google.com/file/d/0BzI7XjAgDAvhMnBDcWJjNWFLbmc/view?usp=sharing)


