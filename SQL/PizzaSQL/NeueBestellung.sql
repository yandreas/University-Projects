Create or replace procedure NeueBestellung(
v_1 in Bestellung.BDatum%type,
v_2 in Bestellung.Lieferzeit%type,
v_3 in Bestellung.Bezahlt%type,
v_4 in Bestellung.BeKundenID%type,
v_5 in bereitet_zu.Bkochid%type,
v_6 in liefert_aus.NLieferantID%type,
v_7 in nimmt_auf.LBestellnehmerID%type
)
is begin
insert into Bestellung values (BIDZähler.nextval, v_1, v_2, v_3, v_4, 0);
insert into bereitet_zu values (v_5, BIDZähler.currval);
insert into liefert_aus values (v_6, BIDZähler.currval);
insert into nimmt_auf values (v_7, BIDZähler.currval);
commit;
end NeueBestellung;