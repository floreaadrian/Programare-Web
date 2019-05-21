<?php
class Report
{
    private $conn;
    private $table = 'logging';

    //field
    public $id;
    public $type;
    public $severity;
    public $user;
    public $date;
    public $log;

    // constructor with db
    public function __construct($db)
    {
        $this->conn = $db;
    }

    //get report
    public function read()
    {
        $querry = 'SELECT * from ' . $this->table;
        $stmt = $this->conn->prepare($querry);
        $stmt->execute();
        return $stmt;
    }

    public function read_single()
    {
        $querry = 'SELECT * from ' . $this->table . ' WHERE id = ?';
        $stmt = $this->conn->prepare($querry);
        $stmt->bindParam(1, $this->id);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
        // Set properties
        $this->type = $row['type'];
        $this->severity = $row['severity'];
        $this->user = $row['user'];
        $this->date = $row['timp'];
        $this->log = $row['log'];
    }

    public function read_paging($page = 1)
    {
        $records_per_page = 2;
        $start_from = ($page - 1) * $records_per_page;
        $querry = 'SELECT * from ' . $this->table . " ORDER BY id LIMIT " .
            $start_from . ", " . $records_per_page;
        $stmt = $this->conn->prepare($querry);
        $stmt->execute();
        return $stmt;
    }

    public function read_by_severity($severity)
    {
        $querry = 'SELECT * from ' . $this->table . " WHERE severity = '$severity'";
        $stmt = $this->conn->prepare($querry);
        $stmt->execute();
        return $stmt;
    }

    public function get_nr_of_reports()
    {
        $query = 'SELECT COUNT(*) as numar FROM' . $this->table;
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt;
    }

    public function create()
    {
        $query = 'INSERT INTO ' . $this->table .
            ' SET type = :type, severity = :severity, user = :user, timp = :date, log= :log';
        // Prepare statement
        $stmt = $this->conn->prepare($query);
        // Clean data
        $this->type = htmlspecialchars(strip_tags($this->type));
        $this->severity = htmlspecialchars(strip_tags($this->severity));
        $this->user = htmlspecialchars(strip_tags($this->user));
        $this->date = htmlspecialchars(strip_tags($this->date));
        $this->log = htmlspecialchars(strip_tags($this->log));
        // Bind data
        $stmt->bindParam(':type', $this->type);
        $stmt->bindParam(':severity', $this->severity);
        $stmt->bindParam(':user', $this->user);
        $stmt->bindParam(':date', $this->date);
        $stmt->bindParam(':log', $this->log);
        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        // Print error if something goes wrong
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    public function delete()
    {
        $query = 'DELETE FROM ' . $this->table . ' WHERE id = :id';
        // Prepare statement
        $stmt = $this->conn->prepare($query);
        //clean data
        $this->id = htmlspecialchars(strip_tags($this->id));
        //bind data
        $stmt->bindParam(':id', $this->id);
        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        // Print error if something goes wrong
        printf("Error: %s.\n", $stmt->error);
        return false;
    }
}
