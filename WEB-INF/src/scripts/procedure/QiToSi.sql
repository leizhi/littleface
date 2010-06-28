use xpcwBranch;
delimiter //

DROP PROCEDURE IF EXISTS QiToSi;
CREATE PROCEDURE QiToSi (INOUT JobID VARCHAR(20),IN bID smallint,OUT RetID VARCHAR(50))
AddEnd:BEGIN

DECLARE Tmp VARCHAR(20) DEFAULT '';
DECLARE MaxNo INT DEFAULT 0;
DECLARE fID INT DEFAULT 0;
DECLARE Type VARCHAR(2) DEFAULT 'SE';
DECLARE Now VARCHAR(10) DEFAULT '0000-00-00';
DECLARE Year VARCHAR(4) DEFAULT '0000';
DECLARE Month VARCHAR(2) DEFAULT '0000';
DECLARE NextID VARCHAR(10) DEFAULT '';
DECLARE NextRightID VARCHAR(5) DEFAULT '';

DECLARE  ListQuotJobContainer CURSOR FOR SELECT ID FROM QuotJobContainer WHERE QuotJobID=JobID AND BranchID=bID;
DECLARE  listQuotJobFreight CURSOR FOR SELECT FreightID FROM QuotJobFreight WHERE QuotJobID=JobID AND BranchID=bID;

SET RetID='The procedure of xpcwBranch.QiToSi err,Please check.';
/**get next Client ID*/
SET Now = CURDATE();
set Year = YEAR(Now);
set Year = RIGHT(Year,2);
set Month = MONTH(Now);

IF LENGTH(Month)<2 THEN SET Month = CONCAT('0', Month); END IF;
SELECT COUNT(ID) INTO MaxNo FROM ClientJob WHERE ID LIKE CONCAT(Type, Year, Month,'%');
IF MaxNo < 1 THEN SET NextID = CONCAT(Type, Year, Month,'1001');
ELSE
SELECT ID INTO Tmp FROM ClientJob WHERE ID LIKE CONCAT(Type, Year, Month,'%') ORDER BY ID DESC LIMIT 1;
SET NextID = LEFT(Tmp,6);
SET NextRightID = RIGHT(Tmp,4) + 1;
IF LENGTH(NextRightID)<5 THEN 
       SET NextID = CONCAT(NextID, NextRightID);
END IF;
END IF;
/**check QuotJob have JobID*/
SELECT COUNT(ID) INTO MaxNo FROM QuotJob WHERE ID=JobID AND BranchID = bID;
IF MaxNo < 1 THEN SELECT 'Not Find The JobID In QuotJob' INTO RetID ; LEAVE AddEnd;
/** do Qi add in Si*/
ELSE
/**Add ClientJob from QuotJob*/
    INSERT INTO ClientJob(ID,RefJobID,CusID,ConsigneeID,NotifyID,JobDate,ShipperSINo,Category,ReceiptPlace,PlaceOfIssue,
DeliveryPlace,FinalDest,LoadPortID,DischargePortID,DeliveryPortID,PONo,OrgCopyNo,PODDate,DateOfIssue,StuffDate,SalesPersonID,
OperatorID,DeliveryAgentID,SelectedBookNo,ModifyDate,IsFCL,FreightStatusID,IsAssigned,Comment,JobStatusID,PrincipalID,
LocalFieldID,BranchID)
    SELECT (SELECT NextID),(SELECT 'NA'),CusID,ConsigneeID,NotifyID,(SELECT Now),ShipperSINo,Category,ReceiptPlace,
PlaceOfIssue,DeliveryPlace,FinalDest,LoadPortID,DischargePortID,(SELECT 0),PONo,'THREE',PODDate,DateOfIssue,StuffDate,
SalesPersonID,OperatorID,(SELECT 0),(SELECT 'NULL'),ModifyDate,IsFCL,(SELECT 0),(SELECT 'N'),Comment,(SELECT 0),(SELECT 0),(SELECT 0),BranchID FROM QuotJob WHERE ID = JobID AND BranchID = bID;
/**Update QuotJob Table for ClientJobID Filed*/
UPDATE QuotJob SET ClientJobID = NextID WHERE ID = JobID AND BranchID = bID;
/**Add ClientJobContainer from QuotJobContainer*/
SELECT COUNT(ID) INTO MaxNo FROM QuotJobContainer WHERE QuotJobID=JobID AND BranchID = bID;
OPEN ListQuotJobContainer;
WHILE MaxNo > 0 DO
FETCH ListQuotJobContainer INTO Tmp;
INSERT INTO ClientJobContainer SELECT (SELECT MAX(ID)+1 FROM ClientJobContainer),(SELECT NextID),ContainerTypeID, (SELECT 'NULL'),ItemID,BranchID FROM QuotJobContainer WHERE ID=Tmp AND BranchID = bID;
SET MaxNo = MaxNo -1;
END WHILE;
CLOSE ListQuotJobContainer;
/*Add Freight,ClientJobFreight from QuotJobFreight*/
SELECT COUNT(FreightID) INTO MaxNo FROM QuotJobFreight WHERE QuotJobID=JobID AND BranchID = bID;
OPEN listQuotJobFreight;
WHILE MaxNo > 0 DO
FETCH listQuotJobFreight INTO Tmp;
SELECT MAX(ID)+1 INTO fID FROM Freight;
INSERT INTO Freight SELECT (SELECT fID),CommodityID,GrossWeight,FinalWeight,NetWeight,WeightUnit,
FinalMeasure,Measurement,ChargeableTypeID,MeasureUnit,NoOfPack,PackUnit,ChargeRate,Marks,Description,CommodityRemark,
BranchID FROM Freight WHERE ID=Tmp AND BranchID = bID;

INSERT INTO ClientJobFreight SELECT (SELECT fID),(SELECT NextID),ItemID,BranchID FROM QuotJobFreight WHERE FreightID=Tmp AND BranchID = bID;

SET MaxNo = MaxNo -1;
END WHILE;
CLOSE listQuotJobFreight;

SELECT NextID INTO JobID;
SELECT '0' INTO RetID;
 END IF;
END AddEnd
 //
