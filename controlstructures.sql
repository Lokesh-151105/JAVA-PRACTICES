DECLARE
   marks NUMBER := 75;
BEGIN
   IF marks >= 90 THEN
      DBMS_OUTPUT.PUT_LINE('Grade: A');
   ELSIF marks >= 75 THEN
      DBMS_OUTPUT.PUT_LINE('Grade: B');
   ELSE
      DBMS_OUTPUT.PUT_LINE('Grade: F');
   END IF;
END;
/