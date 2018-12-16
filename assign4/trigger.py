import uuid
from botocore.vendored import requests
import boto3
from boto3.dynamodb.conditions import Key
import json


def lambda_handler(event, context):
    dynamodb = boto3.resource('dynamodb')
    headers = {
        "Content-Type": "application/json;charset=UTF-8"
    }
    for line in event['Records']:
        if line.get('eventName') == 'INSERT':
            try:
                courseId = line.get('dynamodb').get('NewImage').get('courseId').get('S')
            except:
                courseId = ''
            course = dynamodb.Table('Course')
            response_board = course.query(
                KeyConditionExpression=Key('courseId').eq(courseId)
            )

            registeredStudentNum = len(response_board['Items'])
            request_body = {}
            json_object = line.get('dynamodb').get('NewImage')

            request_body['input'] = json.dumps(json_object)
            request_body['name'] = str(uuid.uuid4())
            request_body['stateMachineArn'] = 'arn:aws:states:us-east-2:540381565181:stateMachine:HelloWorld'

            response = requests.post('https://06pya8zi41.execute-api.us-east-2.amazonaws.com/alpha/execution',
                                     data=json.jumps(request_body), headers=headers)

            print(response.json())

    return {
        'statusCode': 200,
        'body': json.dumps(event)
    }