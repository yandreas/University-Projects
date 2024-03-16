Create or replace procedure NeuerKoch(
v_1 in Mitarbeiter.Gehalt%type,
v_2 in Mitarbeiter.Einstelldatum%type,
v_3 in Mitarbeiter.MVorname%type,
v_4 in Mitarbeiter.MNachname%type,
v_5 in Mitarbeiter.Geburtsdatum%type,
v_6 in Mitarbeiter.Geschlecht%type,
v_7 in Mitarbeiter.MAdresse%type,
v_8 in Mitarbeiter.Überstunden%type,
v_9 in Koch.Sterne%type,
v_10 in Koch.Arbeitserfahrung%type)
is begin
insert into Mitarbeiter values (PersonalIDZähler.nextval, v_1, v_2, v_3, v_4, v_5, v_6, v_7, v_8);
insert into Koch values (PersonalIDZähler.currval, v_9, v_10);
commit;
end NeuerKoch;
