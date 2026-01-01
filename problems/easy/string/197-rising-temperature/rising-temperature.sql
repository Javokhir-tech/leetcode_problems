# Write your MySQL query statement below
with previous_weather_data as 
(
    select
        id,
        recordDate,
        temperature,
        lag(temperature, 1) over (order by recordDate) as previous_temperature,
        lag(recordDate, 1) over (order by recordDate) as previous_date
    from 
        weather
)
select
    id
from
    previous_weather_data
where
    temperature > previous_temperature
and
    recordDate = date_add(previous_date, interval 1 day);
