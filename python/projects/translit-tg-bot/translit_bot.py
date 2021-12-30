from telegram.ext import CommandHandler, MessageHandler, Filters
from telegram.ext.callbackcontext import CallbackContext
from telegram.ext.updater import Updater
from telegram import Update

import logging
from local import token

from translit import translit

logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                     level=logging.INFO)

updater = Updater(token=token, use_context=True)
dispatcher = updater.dispatcher

def start_command(update, context):
    context.bot.send_message(chat_id=update.effective_chat.id, text="Просто напиши мне кракозябру, как 'ghbdtn', и я переведу её тебе как 'привет' ;)")

def translit_msg(update: Update, context: CallbackContext):
    answer = translit(update.message.text)
    context.bot.send_message(chat_id=update.effective_chat.id, text=answer)

start_handler = CommandHandler('start', start_command)
dispatcher.add_handler(start_handler)

translit_handler = MessageHandler(Filters.text & (~Filters.command), translit_msg)
dispatcher.add_handler(translit_handler)

updater.start_polling()
