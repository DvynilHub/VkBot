package ru.develom.bot;

import java.util.ArrayList;
import java.util.List;

public class FuncService {
    /**
     * Функция выдает список  групп (всех или определенной специальности)
     *
     * @author Ливенцева Т.В.
     * @date 9.03.2006
     *
     * @param
     * @return array массив  - gid(id группы), gname(название группы),
     * semestr - на котором сейчас находится группа и id  специальности.
     * sf_name - форма обучения
     * sf_id   - id обучения
     *
     * function SpisokGroup($sid = 0) {
     */
    public List<List<String>> spisokGroup(int sid){
        //ниже должны быть запросы
        String gid = null;
        String gname = null;
        String semestr = null;
        String id_spec = null;
        String sf_name = null;
        String sf_id = null;

        List<String> otv = new ArrayList<String>();
        otv.add(gid);
        otv.add(gname);
        otv.add(semestr);
        otv.add(id_spec);
        otv.add(sf_name);
        otv.add(sf_id);

        List<List<String>> res = null;
        res.add(otv);

        return res;
    }

    /**
     * Поиск ид группы по имени
     * @param name
     * @return
     */
    public String getGroupIdByName(String name){
        for(List <String >group: spisokGroup(0)){
            if (group.get(1).equals(name)){
                return group.get(0);
            }
        }
        return null;
    }

    /**
     *
     * @param idChat
     * @return
     */
    public boolean chatHasGroup(int idChat){
        if (idChat == 123){//проверяем в бд в таблице группы-чаты. Если есть совпадение(ид конфы записан), то true
            return true;
        } else return false;
    }


/**
 *
 *  * Функция выдает расписание студенческой группы на текущий период (определяется автоматически).
 *  * Если $subgroup_id не задан или равен FALSE, то будет выдано расписание для всей группы
 *  * Если две последних переменных не указывать, то результатом будет расписание студ. группы.
 *  * Если указаны все входные переменные, то результатом будет расписание студ. группы на
 *  * определенный день недели с учетом типа недели.
 *  *
 *  * @author Салмин И.Е.
 *  * @date 19.12.2006
 *  *
 *  * @global type $conn2
 * * @param int $semestr номер семестра (1,2)
 *  * @param int $group_id ID группы
 *  * @param int $subgroup_id ID подгруппы
 *  * @param int $examine - если 0, то расписание занятий;
 *  * - если 1, то расписание экзаменов (отключено);
 *  * - если 2, то расписание экзаменов по учебным планам;
 *  * @param int $day день недели [1..7]
 *  * @param int $week тип недели [1-числитель,2- общее,3-знаменатель]
 *  * @return string|array Строка с сообщением об ошибке или ассоциативный многомерный массив
 *  * * Для расписания сессии/экзаменов
 *  * exam_name - Название предмета,
 *  * exam_type - Тип контроля,
 *  * emp_id - ID преподавателя,
 *  * emp_name - ФИО преподавателя,
 *  * exam_date - Дата контроля,
 *  * end_session - Дата завершения сессии
 *  *  * Для расписания группы
 *  * [день_недели][номер_пары][тип_недели] = расписание в виде массива:
 *  * "lec_name"  => название_лекции,
 *  * "lec_type"  => тип_лекции,
 *  * "aud_id" => ид. аудитории,
 *  * "aud_name"  => название аудитории,
 *  * "preps"     => массив_преподавателей,
 *  * "groups" => массив_студ_групп,
 *  * Каждый элемент массива преподавателей содержит следующие поля:
 *  * "id"  => ид. преподавателя,
 *  * "name"   => ФИО преподавателя
 *  * Каждый элемент массива студ. групп содержит следующие поля:
 *  * "id"  => ид. группы,
 *  * "name"   => название преподавателя
 *  * "subgroup"  => номер подгруппы, может отсутствовать
 *
 *  function RaspStudGroup($semestr =0, $group_id =0, $subgroup_id =0, $examine =0, $day =0, $week =0)
 */

}
