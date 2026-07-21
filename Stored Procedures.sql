CREATE OR REPLACE PROCEDURE check_grade(marks IN NUMBER) IS
BEGIN
    IF marks >= 90 THEN
        DBMS_OUTPUT.PUT_LINE('Grade: A');
    ELSIF marks >= 75 THEN
        DBMS_OUTPUT.PUT_LINE('Grade: B');
    ELSIF marks >= 50 THEN
        DBMS_OUTPUT.PUT_LINE('Grade: C');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Grade: F');
    END IF;
END;
/

BEGIN
    check_grade(82);
END;
/