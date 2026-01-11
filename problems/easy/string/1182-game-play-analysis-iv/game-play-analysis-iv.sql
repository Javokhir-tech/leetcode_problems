# Write your MySQL query statement below
with first_logins as (
    select
        player_id,
        event_date,
        datediff(event_date, 
            lag(event_date) over (partition by player_id order by event_date asc)
        ) as date_diff,
        row_number() over (
            partition by player_id
            order by event_date asc
        ) as row_num
    from
        activity
)
select
    round(sum(if(date_diff = 1 and row_num = 2, 1, 0)) / count(distinct player_id), 2) as fraction
from
    first_logins
    