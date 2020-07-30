
Set @value = (select salary from salary_info where id = 8);

select Name
from salary_info
where salary > @value
order by salary DESC
