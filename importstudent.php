<?php
	if(isset($_REQUEST['import_student'])){
		$fname =$_FILES['student']['name']; 
		$batch_id = $_REQUEST['batch_id'];
		$chk_ext = explode('.', $fname);
		if(strtolower($chk_ext[1]) == "csv"){
			$filename = $_FILES['student']['tmp_name'];
        	$handle = fopen($filename, "r");
       
       		$batchid = "batchid=".$batch_id;
        	$student = "student=";
        	while(($data=fgetcsv($handle,1000,","))!=false){
        		$student .= $data[0] .'~'. $data[1] .',';
        	}
        	$url = "https://coursegame.herokuapp.com/rest/student/student-csv";
        	
			$ch = curl_init();

			curl_setopt($ch, CURLOPT_URL,$url);
			curl_setopt($ch, CURLOPT_POSTFIELDS,$student."&".$batchid);
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			$server_output = curl_exec ($ch);
			curl_close ($ch);
			echo $server_output;
		}
		else{
			$res = array();
			$res['status'] = false;
			echo json_encode($res);
		}
	}