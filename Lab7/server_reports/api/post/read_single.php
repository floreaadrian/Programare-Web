<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once '../../config/Database.php';
include_once '../../models/Report.php';

$database = new Database();
$db = $database->connect();

$post = new Report($db);

//get id
$post->id = isset($_GET['id']) ? $_GET['id'] : die();

//get post
$post->read_single();

//return json
$post_array = array(
    'id' => $post->id,
    'type' => $post->type,
    'severity' => $post->severity,
    'user' => $post->user,
    'timp' => $post->date,
    'log' => $post->log
);

print_r(json_encode($post_array));
