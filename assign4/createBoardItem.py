from __future__ import print_function # Python 2/3 compatibility
import uuid
import boto3
import json
import decimal

def lambda_handler(event, context):
    # Helper class to convert a DynamoDB item to JSON.
    class DecimalEncoder(json.JSONEncoder):
        def default(self, o):
            if isinstance(o, decimal.Decimal):
                if abs(o) % 1 > 0:
                    return float(o)
                else:
                    return int(o)
            return super(DecimalEncoder, self).default(o)

    dynamodb = boto3.resource('dynamodb', region_name='us-east-2')

    table = dynamodb.Table('Board')



    response = table.put_item(
        $.Item
    )

    print("PutItem succeeded:")
    print(json.dumps(response, indent=4, cls=DecimalEncoder))

    return {
        'statusCode': 200,
        'body': json.dumps(event)
    }