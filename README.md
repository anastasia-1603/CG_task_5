# CG_task_5
3.Написать программу управления перемещением рисованного объекта клавиатурой.
Пользователь должен иметь возможность самостоятельно создавать любой объект (окружность, многоугольник, текст, загруженное из файла изображение). При этом потребуется применить порождающие паттерны. Сделать так, чтобы добавление нового вида фигуры в программу заключалось просто в создании соответствующего класса.
Ожидается, что при зажатии кнопки на клавиатуре, объект начинает двигаться, а при отпускании - прекращает

a. (25) Простая реализация по описанию выше. Чтобы объект не потерять за границами экрана стоит сделать автоматическое плавное позиционированное. Кроме перемещения можно добавить ещё и поворот.

b. (+10) Можно добавить физику ускорения и замедления. Таким образом, кнопка будет управлять не наличием скорости, а наличием внешнего воздействия. Опять-таки, у внешнего воздействия, наверняка, есть верхняя граница да и изменения могут происходить не линейно.

c. (+15) Можно развить эту программу до небольшой игры, где игрок перемещается по большому прямоугольному миру. Сам мир генерируется динамически в процессе движения игрока и запоминается. При этом, конечно же, потребуется добавить логику проверки пересечения перемещаемого объекта с препятствиями и в зависимости от желаемого результата при пересечении, либо завершать игру, либо просто отскакивать. Цель такой игры - исследовать весь мир. В случае реализации игры ожидается, что при перемещении игрока за ним должна двигаться и камера, как это сделано в играх с видом сверху. Конечно же, камера начинает своё движение при приближению к краю экрана. Если у игрока может меняться скорость, то, вероятно, при увеличении скорости камера должна как бы отдаляться.
