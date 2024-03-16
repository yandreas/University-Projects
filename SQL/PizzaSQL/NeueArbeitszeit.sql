Create or replace procedure NeueArbeitszeit(
v_1 in Arbeitszeit.Datumbeginn%type,
v_2 in Arbeitszeit.Datumende%type,
v_3 in Arbeitet.WPERSONALID%type
)
is begin
insert into Arbeitszeit values (AIDZähler.nextval, v_1, v_2);
insert into Arbeitet values (v_3, AIDZähler.currval);
commit;
end NeueArbeitszeit;