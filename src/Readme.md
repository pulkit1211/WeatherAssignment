Solution :-

1) To implement the CRUD operations for weather data 
  -Enitiy:-Weather

Properties:-
   - private String city
   - private Long temperature
   - private Long humidity
   - private Long wind
   - private String description

2. For Repository we use in-memory HashMap to store the data
3. Services :- WeatherServices and its methods 
  -  WeatherResponseDTO addWeatherData(Weather weather)
  - WeatherResponseDTO findWeatherDataByCity(String city)
  - List<WeatherResponseDTO> getAllData()
  -  WeatherResponseDTO updateWeatherData(String city,Weather weather)
  - boolean deleteWeatherData(String city);

4. DTOs -WeatherResponseDTO
   - The main reason to use DTo instead of Object because for security purpose and we only response and send that info that user wants
   
5. In Mapper Package i create one class to convert orignal object to DTO so mapper help in returning the weather response dto from weather Object

     
     
    
     

