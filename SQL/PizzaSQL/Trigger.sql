create or replace trigger bestellanzahl_erhöhen
after insert
on bestehend_aus
BEGIN
   UPDATE Bestellung
    SET Pizzaanzahl = (select 
                            SUM(anzahl)
                            from bestehend_aus
                            WHERE Bestellung.BID = bestehend_aus.CBID);
END bestellanzahl_erhöhen;    