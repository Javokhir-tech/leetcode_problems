# Write your MySQL query statement below
-- need to find total attemps timeout + confirmed
select
    s.user_id,
    round(avg(case when c.action = 'confirmed' then 1 else 0 end), 2) as confirmation_rate
from
    confirmations c
right join
    signups s
using(user_id)
group by s.user_id
