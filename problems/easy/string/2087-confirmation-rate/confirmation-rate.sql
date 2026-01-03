# Write your MySQL query statement below
-- need to find total attemps timeout + confirmed
with user_total_attempts as (
    select
        s.user_id,
        count(c.action) as total_attempts
    from
        confirmations c
    right join
        signups s
    using(user_id)
    group by s.user_id
),
user_confirmed_attempts as (
    select
        s.user_id,
        sum(case when action = 'confirmed' then 1 else 0 END) AS total_attempts
    from
        confirmations c
    right join
        signups s
    using(user_id)
    group by s.user_id 
),
confirmation_rate as (
    select 
        user_id,
        case 
            when confirmed.total_attempts = 0
        then 0.00
        else
            round(confirmed.total_attempts / total.total_attempts, 2) 
        end as confirmation_rate
    from
        user_confirmed_attempts as confirmed
    join
        user_total_attempts as total
    using(user_id)
    order by user_id desc
)
select 
    *
from 
    confirmation_rate