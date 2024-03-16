select Geschlecht,
count(*) TotalPresent   
from Mitarbeiter
group by Geschlecht;
    
--

select pizzen.pname Pizza, sum(bestehend_aus.anzahl) Bestellanzahl 
from pizzen 
LEFT OUTER JOIN bestehend_aus 
on bestehend_aus.cpname = pizzen.pname 
group by pname, pizzen.pname 
order by pname;

--

select distinct
(select cast(avg(gehalt) as decimal(8,2)) from mitarbeiter, koch where mitarbeiter.personalid = koch.kochid) as Koch, 
(select cast(avg(gehalt) as decimal(8,2)) from mitarbeiter, lieferant where mitarbeiter.personalid = lieferant.lieferantid) as Lieferant,
(select cast(avg(gehalt) as decimal(8,2)) from mitarbeiter, bestellnehmer where mitarbeiter.personalid = bestellnehmer.bestellnehmerid) as Bestellnehmer
from mitarbeiter;



