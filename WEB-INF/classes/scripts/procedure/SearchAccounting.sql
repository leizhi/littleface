use mycozBranch;
delimiter //

DROP PROCEDURE IF EXISTS SearchAccounting;
CREATE PROCEDURE SearchAccounting (IN JobID VARCHAR(20),IN bID smallint,OUT RetID VARCHAR(50))
SearchEnd:BEGIN
/*Search Accounting*/
DECLARE Tmp VARCHAR(20) DEFAULT '';
DECLARE MaxNo INT DEFAULT 0;
DECLARE  listFreight CURSOR FOR SELECT FreightID FROM QuotJobFreight WHERE QuotJobID=JobID AND BranchID=bID;
SET RetID='The procedure of xpcwBranch.DeleteQi err,Please check.';
/**check QuotJob have JobID*/
SELECT COUNT(ID) INTO MaxNo FROM QuotJob WHERE ID=JobID AND BranchID = bID;
IF MaxNo < 1 THEN SELECT 'Not Find The JobID In QuotJob' INTO RetID;LEAVE DeleteEnd;
/**do delete QI*/
ELSE
  DELETE FROM QuotJobContainer WHERE QuotJobID=JobID AND BranchID=bID;
  SELECT COUNT(FreightID) INTO MaxNo FROM QuotJobFreight WHERE QuotJobID=JobID AND BranchID=bID;
   OPEN listFreight;
    WHILE MaxNo > 0 DO
     FETCH listFreight INTO Tmp;
     DELETE FROM QuotJobFreight WHERE FreightID=Tmp AND BranchID=bID;
     DELETE FROM Freight WHERE ID=Tmp AND BranchID=bID;
     SET MaxNo = MaxNo -1;
   END WHILE;
  CLOSE listFreight;
  DELETE FROM QuotDetail WHERE QuotJobID=JobID AND BranchID=bID;
  DELETE FROM QuotJob WHERE ID=JobID AND BranchID=bID;

  SELECT '0' INTO RetID;
 END IF;
END SearchEnd
 //
