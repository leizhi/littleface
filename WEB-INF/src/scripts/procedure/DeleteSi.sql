use xpcwBranch;
delimiter //

DROP PROCEDURE IF EXISTS DeleteSi;
CREATE PROCEDURE DeleteSi (IN JobID VARCHAR(20),IN bID smallint,OUT RetID VARCHAR(50))
DeleteEnd:BEGIN
/*Delete SI*/
DECLARE Tmp VARCHAR(20) DEFAULT '';
DECLARE MaxNo INT DEFAULT 0;
DECLARE  listFreight CURSOR FOR SELECT FreightID FROM ClientJobFreight WHERE ClientJobID=JobID AND BranchID=bID;
SET RetID='The procedure of xpcwBranch.DeleteSi err,Please check.';
/**check ClientJob have JobID*/
/**check if JobID in RefJob,AirHouseBill,SeaHouseBill*/
SELECT COUNT(ID) INTO MaxNo FROM ClientJob WHERE ID=JobID AND BranchID = bID;
IF MaxNo < 1 THEN SELECT 'Not Find The JobID In ClientJob' INTO RetID; LEAVE DeleteEnd;END IF;
SELECT COUNT(ID) INTO MaxNo FROM RefJob WHERE ID=JobID AND BranchID = bID;
IF MaxNo > 0 THEN SELECT 'The JobID In RefJob' INTO RetID; LEAVE DeleteEnd;END IF;
SELECT COUNT(ID) INTO MaxNo FROM AirHouseBill WHERE ClientJobID=JobID AND BranchID = bID;
IF MaxNo > 0 THEN SELECT 'The JobID In AirHouseBill' INTO RetID; LEAVE DeleteEnd;END IF;
SELECT COUNT(ID) INTO MaxNo FROM SeaHouseBill WHERE ClientJobID=JobID AND BranchID = bID;
IF MaxNo > 0 THEN SELECT 'The JobID In AirHouseBill' INTO RetID; LEAVE DeleteEnd;
/**do delete SI*/
ELSE
   UPDATE QuotJob SET ClientJobID = 'NA' WHERE ClientJobID = JobID AND BranchID = bID;
   DELETE FROM ClientJobContainer WHERE ClientJobID=JobID AND BranchID=bID; 
   SELECT COUNT(FreightID) INTO MaxNo FROM ClientJobFreight WHERE ClientJobID=JobID AND BranchID=bID;
   OPEN listFreight;
    WHILE MaxNo > 0 DO
     FETCH listFreight INTO Tmp;
     DELETE FROM ClientJobFreight WHERE FreightID=Tmp AND BranchID=bID;
     DELETE FROM Freight WHERE ID=Tmp AND BranchID=bID;
     SET MaxNo = MaxNo -1;
   END WHILE;
  CLOSE listFreight;
  DELETE FROM ClientJob WHERE ID=JobID AND BranchID=bID;

  SELECT '0' INTO RetID;
 END IF;
END DeleteEnd
 //
