Create or replace procedure NeueArbeitszeit(
v_1 in Arbeitszeit.Datumbeginn%type,
v_2 in Arbeitszeit.Datumende%type,
v_3 in Arbeitet.WPERSONALID%type
)
is begin
insert into Arbeitszeit values (AIDZ�hler.nextval, v_1, v_2);
insert into Arbeitet values (v_3, AIDZ�hler.currval);
commit;
end NeueArbeitszeit;