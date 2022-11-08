def main(
    host: str,
    port: str,
    access_key: str,
    secret_key: str,
    bucket_name: str,
    object_name: str,
    file_path: str,
) -> None:

    from minio.api import Minio

    client = Minio(
        f'{host}:{port}',
        access_key,
        secret_key,
        secure=False,
    )
    try:
        client.fget_object(
            bucket_name,
            object_name,
            file_path,
        )
    except Exception as err:
        print(err)


if __name__ == '__main__':
    main(
        host='localhost',
        port='9000',
        access_key='minio',
        secret_key='miniominio',
        bucket_name='input',
        object_name='201170.csv',
        file_path='./201170.csv',
    )
