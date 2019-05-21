<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once '../../config/Database.php';
include_once '../../models/Report.php';

$database = new Database();
$db = $database->connect();

$post = new Report($db);

//get raw posted data
$data = json_decode(file_get_contents('php://input'));

$post->type = $data->type;
$post->severity = $data->severity;
$post->user = $data->user;
$post->date = $data->timp;
$post->log = $data->log;

if ($post->create()) {
    echo json_encode(
        array('message' => 'Report Created')
    );
} else {
    echo json_encode(
        array('message' => 'Report Not Created')
    );
}
