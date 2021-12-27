from minio import Minio


def main(bucket_name: str, object_name: str, file_path: str) -> None:
    client = Minio(
        'localhost:9000',
        access_key='minio',
        secret_key='miniominio',
        secure=False,
    )
    try:
        client.fput_object(
            bucket_name=bucket_name,
            object_name=object_name,
            file_path=file_path,
            content_type='application/csv',
        )
    except Exception as err:
        print(err)


if __name__ == '__main__':
    main(
        bucket_name='output',
        object_name='17:54-23122021.csv',
        file_path='./17:54-23122021.csv',
    )
