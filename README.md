Приложение имитирует работу кассового аппарата, который формирует чек, исходя из имеющихся данных(покупки, работающий кассир, скидочная карта)
В качестве дополнительных возможностей были разработаны:
1) web-интерфейс
2) возможность найти чек по его id
3) возможность посмотреть лог, содержащий все напечатанные чеки
4) частичное покрытие модульными тестами

В своей работе, приложение использует библиотеку, представляющую собой xml-парсер, разработанную в рамках курса epam jwd. В виде jar файла она находится в папке /lib проекта.
Исходный код проекта: https://github.com/Very-Sad-Owl/JWD_Task03
Все данные хранятся в виде xml файлов.

Порядок запуска приложения:
WEB режим
1. Задеплоить готовый war, находящийся в classes/artifacts/news-0.0.1-SNAPSHOT.war на tomcat server
2. На главной странице по желанию можно сменить язык с русского на английский или наоборот.
3. Перейти на http://localhost:8080/
4. Передать необходимые параметры:
    1) http://localhost:8080/print?prodId1-x&prodId2-y&card_uid=z&cashier_uid=k  ,где prodId - id продукта(можно найти в recources/products.xml), card_uid - id скидочной карты(можно         найти в recources/cards.xml), cashier_uid - id кассира(можно найти в recources/cashiers.xml). Формирует и выводит чек.
    2) http://localhost:8080/log выводит все распечатанные чеки
    3) http://localhost:8080/log?id=x, где x - id чека. Выводит чек с введённым id.

CMD режим
1) Запустить jar файл в папке classes/artifacts/bill-0.0.1-SNAPSHOT.jar в виде java -jar myjar.jar args[]
Необходимые параметры: 
1) action - print, log, find - выбор действия. Остальные параметры аналогичны параметрам в web режиме.

Корректность введённых параметров проверяется, и в случае ошибки, будет выведено соответствующее локализованое сообщение об ошибке.
