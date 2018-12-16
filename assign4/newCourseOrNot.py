import json
def lambda_handler(event, context):
    record = event['Records'][0];
    boardId = record['dynamodb']['NewImage']['boardId']['S'];
    notificationTopic = record['dynamodb']['NewImage']['notificationTopic']['S'];
    listOfRegisteredStudents = record['dynamodb']['NewImage']['listOfRegisteredStudents']['L'];
    department = record['dynamodb']['NewImage']['department']['S'];
    courseId = record['dynamodb']['NewImage']['courseId']['S'];


    event["department"] = department
    event["courseId"] = courseId
    if boardId == "" and notificationTopic == "" and not listOfRegisteredStudents:
        event["isNewCourse"] = 1
    else:
        event["isNewCourse"] = 0

    return {
        'statusCode': 200,
        'body': json.dumps(event)
    }