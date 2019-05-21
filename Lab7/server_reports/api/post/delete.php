<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once '../../config/Database.php';
include_once '../../models/Report.php';

$database = new Database();
$db = $database->connect();

$post = new Report($db);

//get raw posted data
$data = json_decode(file_get_contents('php://input'));

$post->id = $data->id;

if ($post->delete()) {
    echo json_encode(
        array('message' => 'Report deleted')
    );
} else {
    echo json_encode(
        array('message' => 'Report Not deleted')
    );
}
