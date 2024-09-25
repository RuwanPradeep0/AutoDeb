import { Link } from 'expo-router'
import {StatusBar} from 'expo-status-bar'
import {StyleShet , Text ,View} from 'react-native'

export default function App(){
    return(
        <View className = 'flex-1 items-center justify-center bg-white'>
            <Text> Open Appqqqqqqqqqqqqqqqqqqqq</Text>
            <StatusBar style='auto'/>

            <Link href = "/chat"> Go to chat</Link>
            <StatusBar style='auto' />
        </View>
    )
}