use xpcwBranch;
delimiter //

DROP PROCEDURE IF EXISTS ZleiDebug;
CREATE PROCEDURE ZleiDebug (IN JobID VARCHAR(20),IN bID smallint,OUT RetID VARCHAR(50))
FindNextEnd:BEGIN

DECLARE Tmp VARCHAR(20) DEFAULT '';
DECLARE MaxNo INT DEFAULT 0;
DECLARE fID INT DEFAULT 0;
DECLARE Type VARCHAR(2) DEFAULT 'SE';
DECLARE Now VARCHAR(10) DEFAULT '0000-00-00';
DECLARE Year VARCHAR(4) DEFAULT '0000';
DECLARE Month VARCHAR(2) DEFAULT '0000';
DECLARE NextID VARCHAR(10) DEFAULT '';
DECLARE NextRightID VARCHAR(5) DEFAULT '';
/*Next SI*/
SET RetID='The procedure of xpcwBranch.QiToSi err,Please check.';
/**get next Client ID*/
SET Now = CURDATE();
set Year = YEAR(Now);
set Year = RIGHT(Year,2);
set Month = MONTH(Now);

SELECT COUNT(ID) INTO MaxNo FROM ClientJob WHERE ID LIKE CONCAT(Type, Year, Month,'%');
IF MaxNo < 1 THEN SET NextID = CONCAT(Type, Year, Month,'0001');
ELSE 
SELECT ID INTO Tmp FROM ClientJob WHERE ID LIKE CONCAT(Type, Year, Month,'%') ORDER BY ID DESC LIMIT 1;
SET NextID = LEFT(Tmp,6);
SET NextRightID = RIGHT(Tmp,4) + 1;
IF LENGTH(NextRightID)<5 THEN 
       SET NextID = CONCAT(NextID, NextRightID);
END IF;
 END IF;
SELECT NextID;
END FindNextEnd
 //
