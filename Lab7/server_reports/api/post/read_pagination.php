<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once '../../config/Database.php';
include_once '../../models/Report.php';

$database = new Database();
$db = $database->connect();

$post = new Report($db);

//get id
$pagination = isset($_GET['page']) ? $_GET['page'] : 1;

//get post
$result = $post->read_paging($pagination);

$num = $result->rowCount();

if ($num > 0) {
    $posts_arr = array();
    // $posts_arr['data'] = array();
    while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
        extract($row);
        $post_item = array(
            'id' => $id,
            'type' => $type,
            'severity' => $severity,
            'user' => $user,
            'date' => date($timp),
            'log' => $log
        );
        // Push to "data"
        array_push($posts_arr, $post_item);
        // array_push($posts_arr['data'], $post_item);
    }
    // Turn to JSON & output
    echo json_encode($posts_arr);
} else {
    // No Posts
    echo json_encode(
        array('message' => 'No Posts Found')
    );
}
