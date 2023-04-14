accounting_requirements
### «Приложение для создания и обработки требований для получения материалов со склада.»

Приложение содержит три основных объекта: пользователь (User), материал (Material), требование (заявка, Request).
1. Объект Пользователь (User). Имеет следующие поля:
   1. id - уникальный идентификатор пользователя (генерируется автоматически);
   2. name - ФИО пользователя;
   3. departmentNumber - номер подразделения;
   4. email - электронная почта (уникальный параметр);
   5. role - роль (создание или обработка запросов);
2. Объект Материал (Material)
3. Объект Требование (Request)

Приложение выполнено в виде монолита, разбитие по пакетам - по принципу "слоёв".
Использована БД PostgreSQL, для тестов H2.