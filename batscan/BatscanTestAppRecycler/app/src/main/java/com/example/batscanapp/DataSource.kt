package com.example.batscanapp

import com.example.batscanapp.models.Equipment

class DataSource {

    companion object {

        fun createDataSet(): ArrayList<Equipment>{
            val list = ArrayList<Equipment>()
            list.add(
                Equipment(
                    "LIVE #001 - Como ter um perfil de desenvolvedor campeão no Linkedin",
                    "Kaique Ocanha",

                )
            )
            list.add(
                Equipment(
                    "LIVE #002 - A arte de escrever programas legíveis! + 20 dicas práticas! (PARTE 1)",
                    "Kaique Ocanha",

                )
            )
            list.add(
                Equipment(
                    "LIVE #003 - A arte de escrever programas legíveis! + 20 dicas práticas! - (PARTE 2)",
                    "Kaique Ocanha",

                )
            )
            list.add(
                Equipment(
                    "LIVE #004 - Desenvolvedor Android Júnior: o que você precisa saber!",
                    "Kaique Ocanha",

                )
            )
            list.add(
                Equipment(
                    "LIVE #005 - Criando e publicando seu primeiro app Android!",
                    "Kaique Ocanha",

                )
            )
            list.add(
                Equipment(
                    "LIVE #006 - Dominando o RecyclerView com Kotlin no Android!",
                    "Kaique Ocanha",

                )
            )
            return list
        }

    }

}