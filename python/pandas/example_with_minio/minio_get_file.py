from minio.api import Minio


def main(bucket_name: str, object_name: str, file_path: str) -> None:
    client = Minio(
        'localhost:9000',
        access_key='minio',
        secret_key='miniominio',
        secure=False,
    )
    try:
        client.fget_object(
            bucket_name='input',
            object_name='201170.csv',
            file_path='./201170.csv',
        )
    except Exception as err:
        print(err)


if __name__ == '__main__':
    main(
        bucket_name='input',
        object_name='201170.csv',
        file_path='./201170.csv',
    )
